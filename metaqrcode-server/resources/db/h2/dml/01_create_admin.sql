insert into mqr_user (MQR_USER_ID, 
		EMAIL,
		PASSWORD,
		NICKNAME,
		FIRSTNAME,
		LASTNAME
		)
	values (mqr_user_ID_SEQ.nextval, 
		'admin',
		'admin',
		'admin',
		'admin',
		'admin'
		);
		
insert into mqr_user_role (MQR_USER_role_ID, 
		MQR_USER_ID,
		ROLE)
	values (MQR_USER_role_ID_SEQ.nextval, 
		select MQR_USER_ID from mqr_user where EMAIL = 'admin',
		'ROLE_ADMIN');
		
		
insert into mqr_user_role (MQR_USER_role_ID, 
		MQR_USER_ID,
		ROLE)
	values (MQR_USER_role_ID_SEQ.nextval, 
		select MQR_USER_ID from mqr_user where EMAIL = 'admin',
		'ROLE_USER');
		
		
		
