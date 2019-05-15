----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 03/01/2019 06:09:09 PM
-- Design Name: 
-- Module Name: MPG2 - Behavioral
-- Project Name: 
-- Target Devices: 
-- Tool Versions: 
-- Description: 
-- 
-- Dependencies: 
-- 
-- Revision:
-- Revision 0.01 - File Created
-- Additional Comments:
-- 
----------------------------------------------------------------------------------


library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;
-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx leaf cells in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity MPG2 is
    Port ( clk : in STD_LOGIC;
           btn : in STD_LOGIC_VECTOR(4 downto 0);
           enable : out STD_LOGIC_VECTOR(4 downto 0));
end MPG2;

architecture Behavioral of MPG2 is
signal Q:STD_LOGIC_VECTOR(15 downto 0);

signal  D1Out,D2Out,D3Out:STD_LOGIC_VECTOR(4 downto 0);
signal FlipE:std_logic;

begin

process (clk)
 variable mem: std_logic_vector(15 downto 0) := x"0000";
    begin
        if(rising_edge(clk)) then           
                mem:=mem+x"0001";
        end if;
      Q<=mem;
    end process ;
  
 process(CLK,Q)
      begin 
             if(Q=x"FFFF") then 
                FlipE<='1';
             else
                FlipE<='0';
             end if;
      end process ;
 
 process(clk)
 begin 
  if(rising_edge(clk)) then
            if (FlipE='1') then
                D1out<=btn;
             end if;
        end if;
     
 end process; 
 
 process(clk,D1Out)
    begin
     if(rising_edge(clk)) then
        D2out<=D1Out;
     end if;
    end process ;             
  
  process(clk,D2Out)
       begin
        if(rising_edge(clk)) then
           D3out<=D2Out;
        end if;
    end process;             
     
enable<=D2out and (not D3out);    
end Behavioral;