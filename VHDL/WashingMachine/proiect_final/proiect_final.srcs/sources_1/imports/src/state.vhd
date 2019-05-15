---------------------------------------------------------------------------------------------------
--
-- Title       : state
-- Design      : proiect_final
-- Author      : Windows User
-- Company     : dsa
--
---------------------------------------------------------------------------------------------------
--
-- File        : state.vhd
-- Generated   : Sun May  6 23:04:51 2018
-- From        : interface description file
-- By          : Itf2Vhdl ver. 1.20
--
---------------------------------------------------------------------------------------------------
--
-- Description : 
--
---------------------------------------------------------------------------------------------------

--{{ Section below this comment is automatically maintained
--   and may be overwritten
--{entity {state} architecture {state}}

library IEEE;
use IEEE.STD_LOGIC_1164.all;

entity state is
	port( manual,automat,nxt,sel,usa,start,clk_placuta,r: in std_logic;
		i: in std_logic_vector(5 downto 0);
	prespalare,clatire,spalare_p,centrifugare,clatire_s,rotire,incalzire_apa,usa_incuiata, inc_apa, evac_apa: out std_logic;
	o: out std_logic_vector(5 downto 0));
end state;

--}} End of automatically maintained section

architecture state1 of state is	
component selector1
	port(nxt,sel,e: in std_logic;
		aut: out std_logic_vector(2 downto 0));
end component;

component selector2
	port(nxt,sel,e: in std_logic;
		temp: out std_logic_vector(1 downto 0));
end component;

component selector3
	port(nxt,sel,e: in std_logic;
		vit: out std_logic_vector(1 downto 0));
end component;

component selector4
	port(nxt,sel,e: in std_logic;
		O: out std_logic);
end component;

component timer_5_sec
	port( clk_1_sec, e, r: in std_logic;
	q: out std_logic);
end component;

component timer_20_sec
	port( clk_1_sec, e, r: in std_logic;
	q: out std_logic);
end component; 

component timer_30_sec
	port( clk_1_sec, e, r: in std_logic;
	q: out std_logic);
end component;	  

component clock_div is
    Port ( clock_100Mhz : in STD_LOGIC;
	reset : in STD_LOGIC;
	clock_sec:out STD_LOGIC);				
end component; 

signal v_i: std_logic;
signal aut:std_logic_vector(5 downto 0):="000000";	 
signal aut1:std_logic_vector(5 downto 0):="000000";
signal t_5, t_5_v2, t_20, t_30, t_total, en_t_5, en_t_5v2, en_t_20, en_t_30: std_logic;
signal presp,clat,presp1,clat1: std_logic :='0'; 
signal temp:std_logic_vector(1 downto 0):="00";	  
signal temp1:std_logic_vector(1 downto 0):="00";
signal vit:std_logic_vector(1 downto 0):="00"; 
signal vit1:std_logic_vector(1 downto 0):="00";
signal clk_1_sec: std_logic:= '0';


type state_type is (standby, sel_aut, sel_man, started, presp_inc, presp_rot, presp_evac, sp_inc, sp_incalzire, sp_rot, sp_evac, cl_inc, cl_rot, cl_evac, cl_s_inc, cl_s_rot, cl_s_evac, cent, fin );
signal curr_state, next_state: state_type;
begin 

	
	SEC:clock_div port map(clk_placuta,'0',clk_1_sec);
	
	
		
	T5: timer_5_sec port map(clk_1_sec, en_t_5, en_t_5, t_5);
	en_t_5 <= '0' when (curr_state = presp_inc or curr_state = presp_evac or curr_state = sp_evac or curr_state = cl_evac or curr_state = cl_s_evac or curr_state = fin) else '1';
	
    T5v2: timer_5_sec port map(clk_1_sec, en_t_5v2, en_t_5v2, t_5_v2);
	en_t_5v2 <= '0' when (curr_state = sp_inc or curr_state = cl_inc or curr_state = cl_s_inc) else '1';
		
	T20: timer_20_sec port map(clk_1_sec, en_t_20, en_t_20, t_20);
	en_t_20 <= '0' when (curr_state = presp_rot or curr_state = sp_incalzire or curr_state = cl_rot or curr_state = cl_s_rot or curr_state = cent) else '1'; -- T apa
		
	T30: timer_30_sec port map(clk_1_sec, en_t_30, en_t_30, t_30);
	en_t_30 <= '0' when (curr_state = sp_rot) else '1';
		
CLC: process(automat, manual, aut1, presp1, clat1, curr_state, start, usa, sel, t_5, t_5_v2, t_20, t_30, v_i)
     begin
	 case curr_state is
		when standby => if automat = '1' and manual ='0' then
							next_state <= sel_aut;
						elsif automat = '0' and manual ='1' then
							next_state <= sel_man;
						else
							next_state <= standby;
						end if;
		when sel_aut	=> if start = '1' and usa = '1' and v_i = '1' then
							next_state <= started;
						else
							next_state <= sel_aut;	
						end if;					 
		when started	 => if aut = "000100" or presp = '1' then
							next_state <= presp_inc;
						else
							next_state <= sp_inc;
						end if;				
		when presp_inc	 => if t_5 = '1' then
							next_state <= presp_rot;
						else
							next_state <= presp_inc;
						end if;
		when presp_rot	 => if t_20 = '1' then
							next_state <= presp_evac;
						else
							next_state <= presp_rot;
						end if;
		when presp_evac	 => if t_5 = '1' then
							next_state <= sp_inc;
						else
							next_state <= presp_evac;
						end if;
		
		when sp_inc	 => if t_5_v2 = '1' then
							next_state <= sp_incalzire;
						else
							next_state <= sp_inc;
						end if;
		when sp_incalzire  => if t_20  = '1' then					  -- T apa
							next_state <= sp_rot;
						else
							next_state <= sp_incalzire;
						end if;
		when sp_rot	 => if t_30 = '1' then
							next_state <= sp_evac;
						else
							next_state <= sp_rot;
						end if;
		when sp_evac   => if t_5 = '1' then
							next_state <= cl_inc;
						else
							next_state <= sp_evac;
						end if;
		when cl_inc	 => if t_5_v2 = '1' then
							next_state <= cl_rot;
						else
							next_state <= cl_inc;
						end if;
		when cl_rot	 => if t_20 = '1' then
							next_state <= cl_evac;
						else
							next_state <= cl_rot;
						end if;
		when cl_evac	 => if t_5 = '1' then
								if 	 aut = "001000" or aut = "000010" or clat = '1' then
									next_state <= cl_s_inc;
								else
									next_state <= cent;
								end if;
							else
								next_state <= cl_evac;
						end if;
		when cl_s_inc	 => if t_5_v2 = '1' then
							next_state <= cl_s_rot;
						else
							next_state <= cl_s_inc;
						end if;
		when cl_s_rot	 => if t_20 = '1' then
							next_state <= cl_s_evac;
						else
							next_state <= cl_s_rot;
						end if;	 
		when cl_s_evac	 => if t_5 = '1' then
							next_state <= cent;
						else
							next_state <= cl_s_evac;
						end if;
		when cent	 => if t_20 = '1' then
							next_state <= fin;
						else
							next_state <= cent;
						end if;
		when fin	 => if t_5 = '1' then
							next_state <= standby;
						else
							next_state <= fin;
						end if;
		when sel_man  => if start = '1' and usa = '1' and v_i = '1' then
							next_state <= started;
						else
							next_state <= sel_man;	
						end if;
		
				
		end case;		
	end process CLC;
	
	SLC: process(clk_placuta, r, i)
	begin				
	if r = '1' then  
		curr_state <= standby; 
		prespalare <= '0';
		clatire <= '0'; 
		spalare_p <= '0';
		centrifugare <= '0';
		clatire_s <= '0';
		rotire <= '0';
		incalzire_apa <= '0';
		usa_incuiata <= '0';
		inc_apa <= '0';
		evac_apa <= '0';
		o <= "000000";
	else
		if clk_placuta'EVENT and clk_placuta = '1' then
			 case curr_state is				  
		when standby => 
					prespalare <= '0';
					clatire <= '0'; 
					spalare_p <= '0';
					centrifugare <= '0';
					clatire_s <= '0';
					rotire <= '0';
					incalzire_apa <= '0';
					usa_incuiata <= '0';
					inc_apa <= '0';
					evac_apa <= '0';
					o <= "000000";
					aut <= "000000";
					temp <= "00";
					vit <= "00";
					presp <= '0';
					clat <= '0';
		when sel_aut	=> 
					prespalare <= '0';
					clatire <= '0'; 
					spalare_p <= '0';
					centrifugare <= '0';
					clatire_s <= '0';
					rotire <= '0';
					incalzire_apa <= '0';
					usa_incuiata <= '0';
					inc_apa <= '0';
					evac_apa <= '0';
					case i is
						when "100000" => 
							o <= i;
							aut <= i;
							v_i <= '1';
						when "010000" => 
							o <= i;
							aut <= i;
							v_i <= '1';
						when "001000" => 
							o <= i;
							aut <= i;
							v_i <= '1';
						when "000100" => 
							o <= i;
							aut <= i;
							v_i <= '1';
						when "000010" => 
							o <= i;
							aut <= i;
							v_i <= '1';
						when others =>
							o <= "000000";
							aut <= "000000";
							v_i <= '0';
					end case;
						
		when started	 => 
					prespalare <= '0';
					clatire <= '0'; 
					spalare_p <= '0';
					centrifugare <= '0';
					clatire_s <= '0';
					rotire <= '0';
					incalzire_apa <= '0';
					usa_incuiata <= '1';
					inc_apa <= '0';
					evac_apa <= '0';				
		when presp_inc	 =>  
					prespalare <= '1';
					clatire <= '0'; 
					spalare_p <= '0';
					centrifugare <= '0';
					clatire_s <= '0';
					rotire <= '0';
					incalzire_apa <= '0';
					usa_incuiata <= '1';
					inc_apa <= '1';
					evac_apa <= '0';
		when presp_rot	 =>  
					prespalare <= '1';
					clatire <= '0'; 
					spalare_p <= '0';
					centrifugare <= '0';
					clatire_s <= '0';
					rotire <= '1';
					incalzire_apa <= '0';
					usa_incuiata <= '1';
					inc_apa <= '0';
					evac_apa <= '0';
		when presp_evac	 => 
					prespalare <= '1';
					clatire <= '0'; 
					spalare_p <= '0';
					centrifugare <= '0';
					clatire_s <= '0';
					rotire <= '0';
					incalzire_apa <= '0';
					usa_incuiata <= '1';
					inc_apa <= '0';
					evac_apa <= '1';
		
		when sp_inc	 => 
					prespalare <= '0';
					clatire <= '0'; 
					spalare_p <= '1';
					centrifugare <= '0';
					clatire_s <= '0';
					rotire <= '0';
					incalzire_apa <= '0';
					usa_incuiata <= '1';
					inc_apa <= '1';
					evac_apa <= '0';
		when sp_incalzire  => 
					prespalare <= '0';
					clatire <= '0'; 
					spalare_p <= '1';
					centrifugare <= '0';
					clatire_s <= '0';
					rotire <= '0';
					incalzire_apa <= '1';
					usa_incuiata <= '1';
					inc_apa <= '0';
					evac_apa <= '0';
		when sp_rot	 => 
					prespalare <= '0';
					clatire <= '0'; 
					spalare_p <= '1';
					centrifugare <= '0';
					clatire_s <= '0';
					rotire <= '1';
					incalzire_apa <= '0';
					usa_incuiata <= '1';
					inc_apa <= '0';
					evac_apa <= '0';
		when sp_evac   => 
					prespalare <= '0';
					clatire <= '0'; 
					spalare_p <= '1';
					centrifugare <= '0';
					clatire_s <= '0';
					rotire <= '0';
					incalzire_apa <= '0';
					usa_incuiata <= '1';
					inc_apa <= '0';
					evac_apa <= '1';
		when cl_inc	 => 
					prespalare <= '0';
					clatire <= '1'; 
					spalare_p <= '0';
					centrifugare <= '0';
					clatire_s <= '0';
					rotire <= '0';
					incalzire_apa <= '0';
					usa_incuiata <= '1';
					inc_apa <= '1';
					evac_apa <= '0';
		when cl_rot	 => 
					prespalare <= '0';
					clatire <= '1'; 
					spalare_p <= '0';
					centrifugare <= '0';
					clatire_s <= '0';
					rotire <= '1';
					incalzire_apa <= '0';
					usa_incuiata <= '1';
					inc_apa <= '0';
					evac_apa <= '0';
		when cl_evac	 => 
					prespalare <= '0';
					clatire <= '1'; 
					spalare_p <= '0';
					centrifugare <= '0';
					clatire_s <= '0';
					rotire <= '0';
					incalzire_apa <= '0';
					usa_incuiata <= '1';
					inc_apa <= '0';
					evac_apa <= '1';
		when cl_s_inc	 => 
					prespalare <= '0';
					clatire <= '0'; 
					spalare_p <= '0';
					centrifugare <= '0';
					clatire_s <= '1';
					rotire <= '0';
					incalzire_apa <= '0';
					usa_incuiata <= '1';
					inc_apa <= '1';
					evac_apa <= '0';
		when cl_s_rot	 => 
					prespalare <= '0';
					clatire <= '0'; 
					spalare_p <= '0';
					centrifugare <= '0';
					clatire_s <= '1';
					rotire <= '1';
					incalzire_apa <= '0';
					usa_incuiata <= '1';
					inc_apa <= '0';
					evac_apa <= '0'; 
		when cl_s_evac	 => 
					prespalare <= '0';
					clatire <= '0'; 
					spalare_p <= '0';
					centrifugare <= '0';
					clatire_s <= '1';
					rotire <= '0';
					incalzire_apa <= '0';
					usa_incuiata <= '1';
					inc_apa <= '0';
					evac_apa <= '1';
		when cent	 => 
					prespalare <= '0';
					clatire <= '0'; 
					spalare_p <= '0';
					centrifugare <= '1';
					clatire_s <= '0';
					rotire <= '1';
					incalzire_apa <= '0';
					usa_incuiata <= '1';
					inc_apa <= '0';
					evac_apa <= '0';
		when fin	 => 
					prespalare <= '0';
					clatire <= '0'; 
					spalare_p <= '0';
					centrifugare <= '0';
					clatire_s <= '0';
					rotire <= '0';
					incalzire_apa <= '0';
					usa_incuiata <= '0';
					inc_apa <= '0';
					evac_apa <= '0';
		when sel_man  => 
					prespalare <= '0';
					clatire <= '0'; 
					spalare_p <= '0';
					centrifugare <= '0';
					clatire_s <= '0';
					rotire <= '0';
					incalzire_apa <= '0';
					usa_incuiata <= '0';
					inc_apa <= '0';
					evac_apa <= '0';
					
					temp(0) <= i(0);
					temp(1) <= i(1);
					vit(0) <= i(2);
					vit(1) <= i(3);
					presp <= i(4);
					clat <= i(5);
					
					case vit is
						when "11" =>
							v_i <= '0';
							o(2) <= '0';
							o(3) <= '0';
						when others =>
							v_i <= '1';
							o(2) <= vit(0);
							o(3) <= vit(1);
					end case;
					o(0) <= i(0);
					o(1) <= i(1);
					o(4) <= i(4);
					o(5) <= i(5);
					
		end case;		
			curr_state <= next_state;
		end if;	  -- CLK=EVENT and CLK='1'
	end if;--RST
	end process SLC;
	
	
end state1;
