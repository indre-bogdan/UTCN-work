---------------------------------------------------------------------------------------------------
--
-- Title       : timer_20_sec
-- Design      : bistabil_d
-- Author      : Windows User
-- Company     : dsa
--
---------------------------------------------------------------------------------------------------
--
-- File        : timer_20_sec.vhd
-- Generated   : Tue May  1 12:57:58 2018
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
--{entity {timer_20_sec} architecture {timer_20_sec}}

library IEEE;
use IEEE.std_logic_unsigned.all;
use IEEE.STD_LOGIC_1164.all;

entity timer_20_sec is
	port( clk_1_sec, e, r: in std_logic;
	q: out std_logic);
end timer_20_sec;

--}} End of automatically maintained section

architecture timer_20_sec of timer_20_sec is
begin
	process(clk_1_sec,r)
	variable a,b: std_logic_vector(4 downto 0):= "00000";
	begin
		q <= '0';
		if r = '1' then
			a := "00000";
			q <= '0';
		else
			
			if clk_1_sec'event and clk_1_sec = '1' then
			if e = '0' then
				b:= a+1;
				if b/="10101" then
						a:=a + 1;
					else
						q <= '1';
						a:= "00000";
					end if;
			end if;
			end if;
		end if;
	end process;
	 

end timer_20_sec;
