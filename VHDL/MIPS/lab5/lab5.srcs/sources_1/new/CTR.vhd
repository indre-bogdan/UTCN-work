----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 04/05/2019 09:18:15 AM
-- Design Name: 
-- Module Name: CTR - Behavioral
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

entity CTR is
    Port ( 
           Instruction : in STD_LOGIC_VECTOR (2 downto 0);
           RegDst : out STD_LOGIC;
           ExtOp : out STD_LOGIC;
           ALUSrc : out STD_LOGIC;
           Branch : out STD_LOGIC;
           Jump : out STD_LOGIC;
           ALUOp : out STD_LOGIC_VECTOR (2 downto 0);
           MemWrite : out STD_LOGIC;
           MemtoReg : out STD_LOGIC;
           RegWr : out STD_LOGIC);
end CTR;

architecture Behavioral of CTR is

begin

process(Instruction)
begin
case(Instruction) is

   --R
   when "000"=> RegDst <= '1';
                RegWr <= '1';
                AluSrc <= '0';
                Branch <= '0';
                Jump <= '0';
                ExtOp <= '0';
                MemWrite <= '0';
                MemtoReg <= '0';                    
                AluOp <= "000"; 
   --ADDI
   when "001"=> RegDst <= '0';
                RegWr <= '1';
                AluSrc <= '1';
                Branch <= '0';
                Jump <= '0';
                ExtOp <= '1';
                MemWrite <= '0';
                MemtoReg <= '0';                    
                AluOp <= "001"; 
   --LW
   when "010"=> RegDst <= '0';
                  RegWr <= '1';
                  AluSrc <= '1';
                  Branch <= '0';
                  Jump <= '0';
                  ExtOp <= '1';
                  MemWrite <= '0';
                  MemtoReg <= '1';                 
                  AluOp <= "010"; 
   --SW 
   when "011"=> RegDst <= '0';
                   RegWr <= '0';
                   AluSrc <= '1';
                   Branch <= '0';
                   Jump <= '0';
                   ExtOp <= '1';
                   MemWrite <= '1';
                   MemtoReg <= '0';                    
                   AluOp <= "011"; 
   --BEQ
   when "100"=> RegDst <= '0';
                RegWr <= '0';
                AluSrc <= '0';
                Branch <= '1';
                Jump <= '0';
                ExtOp <= '1';
                MemWrite <= '0';
                MemtoReg <= '0';                    
                AluOp <= "100"; 
   --ORI           
   when "101"=> RegDst <= '0';
                 RegWr <= '1';
                 AluSrc <= '1';
                 Branch <= '0';
                 Jump <= '0';
                 ExtOp <= '1';
                 MemWrite <= '0';
                 MemtoReg <= '0';                    
                 AluOp <= "101"; 
  --XORI              
  when "110"=> RegDst <= '0';
                   RegWr <= '1';
                   AluSrc <= '1';
                   Branch <= '0';
                   Jump <= '0';
                   ExtOp <= '1';
                   MemWrite <= '0';
                   MemtoReg <= '0';                    
                   AluOp <= "110"; 
  --JUMP                 
  when "111"=>     RegDst <= '0';
                   RegWr <= '0';
                   AluSrc <= '0';
                   Branch <= '0';
                   Jump <= '1';
                   ExtOp <= '1';
                   MemWrite <= '0';
                   MemtoReg <= '0';                    
                   AluOp <= "111";
end case;
end process;

end Behavioral;
