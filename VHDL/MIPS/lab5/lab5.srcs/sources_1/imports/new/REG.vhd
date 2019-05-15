----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 03/14/2019 08:55:42 PM
-- Design Name: 
-- Module Name: REG - Behavioral
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
use ieee.numeric_std.all;


-- Uncomment the following library declaration if instantiating
-- any Xilinx leaf cells in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity REG is
port (
clk : in std_logic;
ra1 : in std_logic_vector (2 downto 0);
ra2 : in std_logic_vector (2 downto 0);
wa : in std_logic_vector (2 downto 0);
wd : in std_logic_vector (15 downto 0);
wen : in std_logic;
rd1 : out std_logic_vector (15 downto 0);
rd2 : out std_logic_vector (15 downto 0)
);
end REG;
architecture Behavioral of REG is
type reg_array is array (0 to 15) of std_logic_vector(15 downto 0);
signal reg_file : reg_array:=(X"0000",X"0001",X"0010",X"0100",X"1000",others => X"0005");
begin
process(clk)
begin

    if rising_edge(clk) then
        if wen = '1' then
            reg_file(conv_integer(wa)) <= wd;
        end if;
    end if;
end process;
rd1 <= reg_file(conv_integer(ra1));
rd2 <= reg_file(conv_integer(ra2));
end Behavioral; 