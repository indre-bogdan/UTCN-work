library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.std_logic_unsigned.all;   


entity clock_div is
    Port ( clock_100Mhz : in STD_LOGIC;
	reset : in STD_LOGIC;
	clock_sec:out STD_LOGIC);				
end clock_div;	

architecture ar_clock_div of clock_div is 

signal one_second_counter: STD_LOGIC_VECTOR (27 downto 0):=x"0000000";
begin
        process(clock_100Mhz, reset)
begin
        if(reset='1') then
            one_second_counter <= (others => '0');
        elsif(rising_edge(clock_100Mhz)) then
            if(one_second_counter>=x"5F5E0FF") then
                one_second_counter <= (others => '0');
            else
                one_second_counter <= one_second_counter + "0000001";
            end if;
        end if;	
				
end process;
		clock_sec <= '1' when one_second_counter=x"5F5E0FF" else '0';	

end ar_clock_div;