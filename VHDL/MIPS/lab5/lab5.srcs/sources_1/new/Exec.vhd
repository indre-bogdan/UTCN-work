----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 04/11/2019 12:14:47 PM
-- Design Name: 
-- Module Name: Execution_Unit - Behavioral
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

entity Exec is
    Port (
            BranchAddress : out  std_logic_vector (15 downto 0);
            Zero : out  STD_LOGIC;
            AluRezultat : out  std_logic_vector (15 downto 0);
            PC:in std_logic_vector(15 downto 0);
            RD1:in std_logic_vector(15 downto 0);
            RD2:in std_logic_vector(15 downto 0);
            Ext_imm:in std_logic_vector(15 downto 0);
            sa:in std_logic;
            funct:in std_logic_vector(2 downto 0);
            Alu_src:in std_logic;
            AluOp : in  std_logic_vector (2 downto 0)
           
            );
end Exec;

architecture Behavioral of Exec is
signal Mux1OUt:std_logic_vector(15 downto 0); 
signal AluCtrl: std_logic_vector(2 downto 0);
signal AluRes : STD_LOGIC_VECTOR (15 downto 0);
begin

process(AluOp)
begin
case AluOp is
when "000" => 
	case funct is
		when "000" => AluCtrl <= "000";  -- add
		when "001" => AluCtrl <= "001";  -- sub
		when "010" => AluCtrl <= "010";  -- sll
		when "011" => AluCtrl <= "011";  -- srl
		when "100" => AluCtrl <= "100";  -- and
		when "101" => AluCtrl <= "101";  -- or
		when "110" => AluCtrl <= "110";  -- xor
		when "111" => AluCtrl <= "111";  -- nope
		when others => null;
	end case;
when "001" => AluCtrl <= "000";--addi
when "010" => AluCtrl <= "000";--lw
when "011" => AluCtrl <= "000";--sw
when "100" => AluCtrl <= "001";--beq
when "101" => AluCtrl <= "101";--ori
when "110" => AluCtrl <= "110";--xori
when "111" => AluCtrl <= "000";--jump


when others => null;
end case;
end process;

process(ALuCtrl)
    begin
        case AluCtrl is 
            when "000"=>AluRes<=RD1+Mux1Out;
            when "001"=>AluRes<=RD1-Mux1Out;
            when "010"=>if sa='0' then
                            AluRes<=Mux1Out;
                        else
                            AluRes<=Mux1Out(14 downto 0)&'0'; 
                        end if;
            when "011"=>if sa='0' then
                             AluRes<=Mux1Out;
                        else
                             AluRes<= '0' & Mux1Out(15 downto 1); 
                        end if;
            when "100"=>AluRes<=Mux1Out and RD1;
            when "101"=>AluRes<=Mux1Out or RD1;
            when "110"=>AluRes<=Mux1Out xor RD1; 
            when "111"=> null;
     
end case;
end process;

process(Alu_src)
    begin 
    
    if Alu_src='0' then
        Mux1OUt<=RD2;
    else
        Mux1Out<=Ext_Imm;
    end if;
end process;

Zero <= '1' when AluRes = x"0000" else '0';
AluRezultat <= AluRes;
BranchAddress <= PC + Ext_Imm;

end Behavioral;
