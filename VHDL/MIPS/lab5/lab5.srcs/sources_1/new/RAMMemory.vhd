----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 04/16/2019 07:47:16 PM
-- Design Name: 
-- Module Name: Memory_unit - Behavioral
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
use ieee.std_logic_arith.all;
use ieee.std_logic_unsigned.all;

-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx leaf cells in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity RAMMemory is
    Port (clk:in std_logic; 
          MemWrite : in std_logic;--enable
          AluRes:in std_logic_vector(15 downto 0); --address
          RD2:in std_logic_vector(15 downto 0);    --write data
          MemData:out std_logic_vector(15 downto 0);
          enable:in std_logic_vector(4 downto 0)
            );
end RAMMemory;

architecture Behavioral of RAMMemory is
type ram_type is array (0 to 255) of std_logic_vector (15 downto 0);
signal RAM: ram_type:=(x"2000",x"2001",x"2002",x"2003",x"2004",others=>x"0000");
begin

 process (clk)
    begin
        if clk'event and clk = '1' then
            if MemWrite = '1' then
                if enable(4) = '1' then
                        RAM(conv_integer(AluRes)) <= RD2;
                

                  end if;
            end if;
         end if;
 end process; 
 MemData <= RAM( conv_integer(AluRes));
end Behavioral;
