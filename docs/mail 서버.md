<details>
  <summary>용어설명</summary>

smtp
- `메일을 주고 받을때 사용되는 프로토콜을 구현한 서버고 정해진 규칙대로 서버에 전송하면 메일 발송해준다.`

starttls
- `암호화 안된 연결을 암호화 해주는 방법이다.`
</details>

<details>
<summary>smtp 포트</summary>

- 587 (starttls 포트)
- 465 (ssl 포트)
</details>
<details>
  <summary>gmail smtp 서버 사용방법</summary>
  
- application.yml 설정
```
spring:
    # mail 발송 속성
    mail:
        host: smtp.gmail.com
        port: 587
        username: ${EMAIL}
        password: ${APP_PASSWORD}
        properties:
            mail.smtp.auth: true
            mail.smtp.connectiontimeout: 5000
            mail.smtp.timeout: 5000
            mail.smtp.starttls.enable: true
```
- [app password 발급](https://myaccount.google.com/apppasswords?pli=1&rapt=AEjHL4M290zKbrnq4oDBuJmMONt-pQuCS6iRsdkUf3VKg-mWS4Q1B5v7rwqKdiGHM0O79tVaLu-woNAC08w5JMmwp7Z4NcCZvQDdpJqXGpKrkkYs_gr3bx8)

- gmail 계정에서 imap 사용

smtp 에러 참고
1. [에러코드 설명](https://support.google.com/a/answer/3726730?sjid=13395282913677918031-AP)
</details>

#### TIP
- `smtp는 보안을 고려안해서 starttls 방식으로 암호화 연결을 제공하여 보안을 제공함`