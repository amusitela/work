create table `ry-activiti`.app_flow
(
    account_time mediumtext   null,
    account_type varchar(10)  null,
    amount       double       null,
    id           int auto_increment
        primary key,
    money_type   varchar(2)   null,
    whereabouts  varchar(100) null,
    used_card    varchar(100) null,
    constraint app_flow_id_uindex
        unique (id)
);


