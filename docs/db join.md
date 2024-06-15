#### join할때 디비 부하 주는 case
left outer join시 left는 무조건 출력이 된다. 근데 조건으로 왼쪽 테이블과 일치하는 데이터를 결합한다고 했을때
왼쪽 테이블 데이터가 중복되어 출력된다. 이는 디비 성능으로 문제가 있을 수 있다.


예를들어 post 테이블에 comment가 10개고, tag가 10개 있을때 post 테이블을 left outer join하면 중복된 post가 100개가 나온다.

```
select * from post left join post.id = comment.post_id left join post.id = tag.post_id
```

디비는 모듈서버처럼 증설이 동기화 이슈로 쉽지 않기 때문에 가급적이면 디비에 부담을 주는 행위는 피해야한다.
