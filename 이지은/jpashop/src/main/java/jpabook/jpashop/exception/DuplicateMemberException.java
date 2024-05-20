package jpabook.jpashop.exception;

import lombok.Getter;

@Getter
public class DuplicateMemberException extends RuntimeException{

    private final MemberErrorCode errorCode;
    public DuplicateMemberException(String message, MemberErrorCode errorCode){
        super(message);
        this.errorCode = errorCode;
    }
    
    /*
    public MemberErrorCode getErrorCode(){
        return errorCode;
    }
     */
}
