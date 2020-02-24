global Java_PerformanceTest_computeArrayMul

section .data

    GetIntArrayElements:     equ 187 * 8
    ReleaseIntArrayElements: equ 195 * 8

section .text

Java_PerformanceTest_computeArrayMul:

    push r8                   
    push rcx                  
    push r9                   
    mov rdx, r8               
    mov rax, [rcx]            
    xor r8d, r8d              
    sub rsp, 32 + 8           
    call [rax + GetIntArrayElements]
    add rsp, 32 + 8           
    pop rcx                   
    lea rcx, [rax + 4 * rcx]  
    mov r8, rax               
	mov rax,1
    add_element:
        movsxd r9, dword [r8]
        mul r9           
        add r8, 4            
        cmp r8, rcx          
        jne add_element
    pop rcx                   
    pop rdx                  
    push rax                  
    mov rax, [rcx]            
    sub rsp, 32 + 8           
    call [rax + ReleaseIntArrayElements]
    add rsp, 32 + 8           
    pop rax                   
    ret
