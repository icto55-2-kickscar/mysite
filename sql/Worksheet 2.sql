		    select  a.no, a.title, a.reg_date, b.no, b.name, b.email 
		     from  board a,
		 	         member b
		   where	 a.member_no = b.no
		      and  a.title like '%첫%'
		order by  a.reg_date desc;