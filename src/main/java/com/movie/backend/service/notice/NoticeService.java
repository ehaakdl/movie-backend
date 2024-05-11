package com.movie.backend.service.notice;
// TODO 알림수단을 하나의 타입으로 wrapping 시켜야함 사용할때
// slack, email, kakaotalk 등 NoticeService 타입으로 다 받을 수 있어야한다.
// 필요한 서비스 스케쥴러에서 등록된 모든 알림 서비스로 보낼 수 있어야한다.
public interface NoticeService {
    public void notice();
}
