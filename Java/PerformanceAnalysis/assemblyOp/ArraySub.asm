global Java_PerformanceTest_computeArraySub

section .data

    GetIntArrayElements:     equ 187 * 8
    ReleaseIntArrayElements: equ 195 * 8

section .text

Java_PerformanceTest_computeArraySub:

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
    xor r8d, r8d              
    sub_element:
        movsxd r9, dword [rax]
        sub r8, r9            
        add rax, 4            
        cmp rax, rcx          
        jne sub_element
    pop rcx                   
    pop rdx                   
    push r8                  
    mov rax, [rcx]           
    sub rsp, 32 + 8           
    call [rax + ReleaseIntArrayElements]
    add rsp, 32 + 8           
    pop rax                  
    ret
