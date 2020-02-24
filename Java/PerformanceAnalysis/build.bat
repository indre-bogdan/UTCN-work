@echo off

del libPerformanceTest.so

nasm -f win64 -o ArraySum.o assemblyOp\ArraySum.asm
nasm -f win64 -o ArraySub.o assemblyOp\ArraySub.asm
nasm -f win64 -o ArrayMul.o assemblyOp\ArrayMul.asm
nasm -f win64 -o ArrayAnd.o assemblyOp\ArrayAnd.asm
nasm -f win64 -o ArrayOr.o assemblyOp\ArrayOr.asm
nasm -f win64 -o ArrayXor.o assemblyOp\ArrayXor.asm

gcc -shared -o libPerformanceTest.so ArraySum.o ArraySub.o ArrayMul.o ArrayAnd.o ArrayOr.o ArrayXor.o

del ArraySum.o
del ArraySub.o
del ArrayMul.o
del ArrayAnd.o
del ArrayOr.o
del ArrayXor.o

