---------------------------------------------------------------------------------------------------
--
-- Title       : timer_5_sec
-- Design      : bistabil_d
-- Author      : Windows User
-- Company     : dsa
--
---------------------------------------------------------------------------------------------------
--
-- File        : timer_5_sec.vhd
-- Generated   : Tue May  1 12:43:44 2018
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
--{entity {timer_5_sec} architecture {timer_5_sec}}

library IEEE;
use IEEE.std_logic_unsigned.all;
use IEEE.STD_LOGIC_1164.all;

entity timer_5_sec is
	port( clk_1_sec, e, r: in std_logic;
	q: out std_logic);
end timer_5_sec;

--}} End of automatically maintained section

architecture timer_5_sec of timer_5_sec is
begin
   	process(clk_1_sec,r)
	variable a,b: std_logic_vector(2 downto 0):= "000";
	begin
		q <= '0';
		if r = '1' then
			a := "000";
			q <= '0';
		else
			
			if clk_1_sec'event and clk_1_sec = '1' then
			         if e ='0' then
				b:= a+1;
				if b/="101" then
						a:=a + 1;
					else
						q <= '1';
						a:= "000";
					end if;
			end if;
			end if;
		end if;
	end process;
end timer_5_sec;
