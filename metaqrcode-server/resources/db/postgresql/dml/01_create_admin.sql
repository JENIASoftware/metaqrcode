insert into mqr_user (MQR_USER_ID, 
		EMAIL,
		PASSWORD,
		NICKNAME,
		FIRSTNAME,
		LASTNAME
		)
	values (nextval('mqr_user_ID_SEQ'), 
		'admin',
		'admin',
		'admin',
		'admin',
		'admin'
		);
		
insert into mqr_user_role (MQR_USER_role_ID, 
		MQR_USER_ID,
		ROLE)
	values (nextval('MQR_USER_role_ID_SEQ'), 
		(select MQR_USER_ID from mqr_user where EMAIL = 'admin'),
		'ROLE_ADMIN');
		
		
insert into mqr_user_role (MQR_USER_role_ID, 
		MQR_USER_ID,
		ROLE)
	values (nextval('MQR_USER_role_ID_SEQ'), 
		(select MQR_USER_ID from mqr_user where EMAIL = 'admin'),
		'ROLE_USER');
		
		
		
