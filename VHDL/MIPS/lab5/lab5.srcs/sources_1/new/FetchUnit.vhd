----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 03/29/2019 08:26:37 AM
-- Design Name: 
-- Module Name: FetchUnit - Behavioral
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

entity FetchUnit is
       Port ( 
            clk: in STD_LOGIC;
            reset: in STD_LOGIC;
            enable: in STD_Logic_vector(4 downto 0);
            JumpAddress : in STD_LOGIC_VECTOR (15 downto 0);
           BranchAddress : in STD_LOGIC_VECTOR (15 downto 0);
           JumpCond : in STD_LOGIC;
           PCSrc : in STD_LOGIC;
           Instruction : out STD_LOGIC_VECTOR (15 downto 0);
           PC : out STD_LOGIC_VECTOR (15 downto 0));
end FetchUnit;

architecture Behavioral of FetchUnit is
signal aux: STD_LOGIC_VECTOR(15 downto 0);
signal mux:std_logic_vector(15 downto 0);
signal nextA:std_logic_vector(15 downto 0);
SIGNAL PC2:std_logic_vector(15 downto 0);
type rom_type is array(0 to 256) of std_logic_vector(15 downto 0);
signal ROM:rom_type:=
(
                  
B"000_001_010_001_0_000", --0  0510 $1 <- $1 + $2
B"000_001_010_011_0_001", --1  0531 $3 <- $2 - $1
B"000_000_010_010_1_010", --2  012A $2 <- $2 << 1
B"000_000_011_011_1_011", --3  01BB $3 <- $3 >> 1
B"000_001_011_011_0_110", --4  05B6 $3 <- $1 ^ $3  
B"000_010_011_011_0_101", --5  09B5 $3 <- $2 | $3
B"000_001_010_001_0_100", --6  0514 $1 <- $1 & $2
B"001_001_001_0000101",   --7  2485 $1 <- $1 + 5
B"011_011_001_0000001",   --8  6C81 M[$3 + 1] <- $1
B"010_011_010_0000001",   --9  4D01 $2 <- M[$3 + 1]
B"100_001_010_0000010",   --10 8502 beq $1 $2 2
B"000_000_000_000_0_111", --11 0007 nop
B"000_000_000_000_0_111", --12 0007 nop
B"101_001_001_0000011",   --13 A483 $1 <- $1 | 3
B"111_0000000010100",     --14 E014 jmp 20
B"000_000_000_000_0_111", --15 0007 nop
B"000_000_000_000_0_111", --16 0007 nop
B"000_000_000_000_0_111", --17 0007 nop
B"000_000_000_000_0_111", --18 0007 nop
B"000_000_000_000_0_111", --19 0007 nop
B"110_010_010_0000011",   --20 C903 $2 <- $2 ^ 3
B"000_000_000_000_0_111", --21 E004 nop
B"000_001_010_001_0_000", --22      $1 <- $1 + $2
B"000_000_001_001_1_011", --23      $1 <- $1 : 2
B"111_0000000000000",     --24 E004 jmp 0

others => X"1000");

signal adress:std_logic_vector(7 downto 0);
signal ROM_O:std_logic_vector(15 downto 0);

begin
process (clk, reset)
    begin
        if(reset = '1') then
           PC2<=x"0000";
            
        elsif(rising_edge(clk)) then
            if(enable(1)='1') then
                PC2<=nextA;
             end if;
        end if;
                          
 end process; 
  process(clk)
     begin
         case PCSrc is
            when '0'=>mux<=aux;
            when '1'=>mux<=BranchAddress;
         end case;
    end process;
    
    process(clk)
         begin 
            case JumpCond is
            when '0'=>nextA<=mux;
            when '1'=>nextA<=JumpAddress;
            
            end case;
    
    end process;

aux<=PC2+1;
PC<=aux;

ROM_O<=ROM(conv_integer(adress));  
adress<=PC2(7 downto 0);
Instruction <= ROM_O;

end Behavioral;
