SELECT * FROM MEMBER;

SELECT * FROM board;

select  a.title, a.reg_date, b.no, b.name, b.email 
 from  board a,
 	     member b
where	 a.member_no = b.no;
	 