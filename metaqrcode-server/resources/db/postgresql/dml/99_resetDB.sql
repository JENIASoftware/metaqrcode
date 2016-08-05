delete from mqr_catalog_stat;

delete from mqr_link;

delete from mqr_catalog_catalog;

delete from mqr_repository_catalog;

delete from mqr_repository;

delete from mqr_catalog;

delete from mqr_user_role where mqr_user_id in (select mqr_user_id from mqr_user where email != 'admin');

delete from mqr_user where email != 'admin';

alter sequence mqr_catalog_id_seq restart 1;

alter sequence mqr_catalog_stat_id_seq restart 1;

alter sequence mqr_catalog_vote_id_seq restart 1;

alter sequence mqr_repository_id_seq restart 1;

