package jpabook.jpashop.exception;

public enum MemberErrorCode {
    DUPLICATE_MEMBER("이미 존재하는 회원입니다");

    private final String message;

    MemberErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
