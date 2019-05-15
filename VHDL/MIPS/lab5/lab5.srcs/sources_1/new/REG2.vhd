----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 04/05/2019 08:40:25 AM
-- Design Name: 
-- Module Name: REG2 - Behavioral
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


-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx leaf cells in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity REG2 is
    Port ( clk: in std_logic;
           Instr : in STD_LOGIC_VECTOR (15 downto 0);
           RegWrite : in STD_LOGIC;
           RegDst : in STD_LOGIC;
           ExtOp : in STD_LOGIC;
           RD1 : out STD_LOGIC_VECTOR (15 downto 0);
           RD2 : out STD_LOGIC_VECTOR (15 downto 0);
           WD : in STD_LOGIC_VECTOR (15 downto 0);
           ExtImm : out STD_LOGIC_VECTOR (15 downto 0);
           func : out STD_LOGIC_VECTOR (2 downto 0);
           sa : out STD_LOGIC);
end REG2;

architecture Behavioral of REG2 is

component REG is
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
end component;
signal wa2 : std_logic_vector (2 downto 0);
begin

C3:REG port map(clk, Instr(12 downto 10), Instr(9 downto 7), wa2,  wd, RegWrite, RD1, RD2);

wa2 <= Instr(9 downto 7) when RegDst = '0' else Instr(6 downto 4);
sa <= Instr(3);
func <= Instr(2 downto 0);

process(clk,ExtOp)
begin
    if ExtOp='0'then
        ExtImm<=Instr(6 downto 0)&"000000000";
    else
        if(Instr(6)='1') then
            ExtImm<="111111111"&Instr(6 downto 0);
        else
            ExtImm<="000000000"&Instr(6 downto 0);
        end if;
    end if;
        
     
end process;
end Behavioral;
