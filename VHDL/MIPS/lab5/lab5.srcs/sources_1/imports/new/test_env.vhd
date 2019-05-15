----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 02/28/2019 11:14:44 AM
-- Design Name: 
-- Module Name: test_env - Behavioral
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

entity test_env is
    Port ( clk : in STD_LOGIC;
           btn:in STD_LOGIC_VECTOR(4 downto 0);
           sw : in STD_LOGIC_VECTOR (15 downto 0);
           cat : out STD_LOGIC_VECTOR (6 downto 0);
           an : out STD_LOGIC_VECTOR (3 downto 0);
           led : inout STD_LOGIC_VECTOR (15 downto 0):=x"0000");     
      
          
   
end test_env;

architecture Behavioral of test_env is


component LCD is
Port (     clk : in STD_LOGIC;
           d0 : in STD_LOGIC_VECTOR (3 downto 0);
           d1 : in STD_LOGIC_VECTOR (3 downto 0);
           d2 : in STD_LOGIC_VECTOR (3 downto 0);
           d3 : in STD_LOGIC_VECTOR (3 downto 0);
           cat : out STD_LOGIC_VECTOR (6 downto 0);
           an : out STD_LOGIC_VECTOR (3 downto 0));
end component;


component MPG2 is
    Port ( clk : in STD_LOGIC;
           btn : in STD_LOGIC_VECTOR(4 downto 0);
           enable : out STD_LOGIC_VECTOR(4 downto 0));
end component;

component FetchUnit is
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
end component;

signal enable:STD_Logic_vector(4 downto 0):="00000";
signal digits: STD_LOGIC_VECTOR(15 downto 0);
signal Instruction : STD_LOGIC_VECTOR (15 downto 0);
signal PC : STD_LOGIC_VECTOR (15 downto 0);
signal reset:std_logic;
signal aux:std_logic_vector(15 downto 0);
signal RegDst :  STD_LOGIC;
signal ExtOp :  STD_LOGIC;
signal ALUSrc :  STD_LOGIC;
signal Branch :  STD_LOGIC;
signal Jump :  STD_LOGIC;
signal MemWrite :  STD_LOGIC;
signal MemtoReg :  STD_LOGIC;
signal RegWr :  STD_LOGIC;
signal RegWrEn: STD_LOGIC;
signal RD1 :  STD_LOGIC_VECTOR (15 downto 0);
signal RD2 :  STD_LOGIC_VECTOR (15 downto 0);
signal Ext_Imm :  STD_LOGIC_VECTOR (15 downto 0);
signal func :  STD_LOGIC_VECTOR (2 downto 0);
signal sa :  STD_LOGIC;
signal WD : STD_LOGIC_VECTOR (15 downto 0);
signal ALUOp :STD_LOGIC_VECTOR (2 downto 0);

signal afisare:std_logic_vector(15 downto 0);
signal BranchAddress:STD_LOGIC_VECTOR (15 downto 0);
signal Zero:std_logic;
signal AluRezultat:std_logic_vector(15 downto 0);
signal BranchCondition:std_logic;
signal JumpAddress:std_logic_vector(15 downto 0);
signal Memdata:std_logic_vector(15 downto 0);

component CTR is
    Port ( Instruction : in STD_LOGIC_VECTOR (2 downto 0);
           RegDst : out STD_LOGIC;
           ExtOp : out STD_LOGIC;
           ALUSrc : out STD_LOGIC;
           Branch : out STD_LOGIC;
           Jump : out STD_LOGIC;
           ALUOp : out STD_LOGIC_VECTOR (2 downto 0);
           MemWrite : out STD_LOGIC;
           MemtoReg : out STD_LOGIC;
           RegWr : out STD_LOGIC);
end component;
component REG2 is
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
end component;

component Exec is
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
end component;

component RAMMemory is
    Port (clk:in std_logic; 
          MemWrite : in std_logic;
          AluRes:in std_logic_vector(15 downto 0); 
          RD2:in std_logic_vector(15 downto 0);    
          MemData:out std_logic_vector(15 downto 0);
          enable:in std_logic_vector(4 downto 0)
            );
end component;

begin

C2: MPG2 port map(clk => clk,btn => btn,enable => enable);
C3: LCD port map( clk => clk,d0 => digits(3 downto 0),d1 => digits(7 downto 4),d2 => digits(11 downto 8),d3 => digits(15 downto 12), cat =>  cat, an => an);
C4: FetchUnit port map(clk => clk, reset => reset, enable => enable, JumpAddress => JumpAddress ,BranchAddress => BranchAddress ,JumpCond => Jump,PCSrc => BranchCondition,Instruction => Instruction,PC => PC);
C5: REG2 port map(clk => clk,Instr => Instruction, RegWrite => RegWrEn,RegDst => RegDst, ExtOp => ExtOp,RD1 => RD1,RD2 => RD2,WD => WD, ExtImm => Ext_Imm, func => func, sa => sa);
C6: CTR port map(Instruction => Instruction(15 downto 13),RegDst => RegDst,ExtOp => ExtOp,ALUSrc => ALUSrc,Branch => Branch,Jump => Jump,ALUOp => ALUOp,MemWrite => MemWrite,MemtoReg => MemtoReg,RegWr => RegWr);
C7: Exec port map(BranchAddress => BranchAddress, Zero => Zero,AluRezultat => AluRezultat, PC => PC,  RD1 => RD1, RD2 => RD2, Ext_imm => Ext_Imm, sa => sa, funct => func, Alu_src => ALUSrc, AluOp => ALUOp);
C8: RAMMemory port map(clk => clk,MemWrite => MemWrite, AluRes => AluRezultat, RD2 => RD2,MemData => MemData, enable => enable);
reset<=enable(0);

 process(clk)
    begin
    if rising_edge(clk) then
        if enable(3) = '1' then
            RegWrEn <= RegWr;
        else 
            RegWrEn <= '0';
        end if;
    end if;
end process;
 
 process(clk)
begin case sw (7 downto 5) is
              when "000" => digits <= Instruction;
			  when "001" => digits<=PC;
			  when "010" => digits<=RD1;
			  when "011" => digits<=RD2;
			  when "100" => digits<=Ext_Imm;       
			  when "101" => digits<=AluRezultat;              
			  when "110" => digits<=MemData;
			  when "111" => digits<=WD;
			  when others => digits<=x"AAAA" ;
			end case; 
end process;

process (clk)
   begin
    if  rising_edge(clk) then
       if sw(0) = '0'  then
            led(0) <= RegDst;
            led(1) <= ExtOp;
            led(2) <= AluSrc;
            led(3) <= Branch;
            led(4) <= Jump;
            led(5) <= MemWrite;
            led(6) <= MemtoReg;
            led(7) <= RegWr;
            led(8) <=BranchCondition;

        else
            led(2  downto 0) <= AluOp ;
        end if;
    end if;
end process;
    
WD <= AluRezultat when MemtoReg='0' else MemData ;
BranchCondition <=  Zero and  Branch;
JumpAddress <= PC(15 downto 13) & Instruction(12 downto 0);


end Behavioral;