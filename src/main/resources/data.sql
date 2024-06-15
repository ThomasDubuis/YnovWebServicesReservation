insert into cinema (uid, name, created_at, updated_at) values ('11a1e254-349a-4e8a-ab75-a9ac0d44e1b1', 'Le Cezanne', '2024-01-01 11:11:11.111', '2024-01-01 11:11:11.111');
insert into cinema (uid, name, created_at, updated_at) values ('22a2e254-349a-4e8a-ab75-a9ac0d44e2b2', 'Le Renoir', '2024-01-01 11:11:11.111', '2024-01-01 11:11:11.111');
insert into cinema (uid, name, created_at, updated_at) values ('33a3e254-349a-4e8a-ab75-a9ac0d44e3b3', 'Pathe plan de campagne', '2024-01-01 11:11:11.111', '2024-01-01 11:11:11.111');

insert into room (uid, name, cinema_id, seats, created_at, updated_at) values ('5fee4c75-a06a-4d4c-b65b-d3d23958cc85', 'Salle 1 - Cezanne', '11a1e254-349a-4e8a-ab75-a9ac0d44e1b1', 120, '2024-01-01 11:11:11.111', '2024-01-01 11:11:11.111');
insert into room (uid, name, cinema_id, seats, created_at, updated_at) values ('495882ec-bc7c-4fd5-a6ae-02dde1b05366', 'Salle 2 - Cezanne', '11a1e254-349a-4e8a-ab75-a9ac0d44e1b1', 120, '2024-01-01 11:11:11.111', '2024-01-01 11:11:11.111');
insert into room (uid, name, cinema_id, seats, created_at, updated_at) values ('0ad88ea8-8d8e-4a89-8bf8-d3eed90e1e4b', 'Salle 3 - Cezanne', '11a1e254-349a-4e8a-ab75-a9ac0d44e1b1', 80, '2024-01-01 11:11:11.111', '2024-01-01 11:11:11.111');
insert into room (uid, name, cinema_id, seats, created_at, updated_at) values ('aea96ae9-4626-4742-8652-ca1f0c55970a', 'Salle 4 - Cezanne', '11a1e254-349a-4e8a-ab75-a9ac0d44e1b1', 60, '2024-01-01 11:11:11.111', '2024-01-01 11:11:11.111');

insert into room (uid, name, cinema_id, seats, created_at, updated_at) values ('9f4ff0dc-67ad-40b4-a7e8-7755bb97a4ac', 'Salle 1 - Renoir', '22a2e254-349a-4e8a-ab75-a9ac0d44e2b2', 80, '2024-01-01 11:11:11.111', '2024-01-01 11:11:11.111');
insert into room (uid, name, cinema_id, seats, created_at, updated_at) values ('d34d0e7c-a5cd-4ea0-a05c-66586833c461', 'Salle 2 - Renoir', '22a2e254-349a-4e8a-ab75-a9ac0d44e2b2', 30, '2024-01-01 11:11:11.111', '2024-01-01 11:11:11.111');

insert into room (uid, name, cinema_id, seats, created_at, updated_at) values ('3913e7f9-a7be-4fa8-8f9f-14c2a535775b', 'Salle 1 - Pathe', '33a3e254-349a-4e8a-ab75-a9ac0d44e3b3', 150, '2024-01-01 11:11:11.111', '2024-01-01 11:11:11.111');
insert into room (uid, name, cinema_id, seats, created_at, updated_at) values ('f53118cc-34fd-4fd5-95f6-7b627d764117', 'Salle 2 - Pathe', '33a3e254-349a-4e8a-ab75-a9ac0d44e3b3', 120, '2024-01-01 11:11:11.111', '2024-01-01 11:11:11.111');
insert into room (uid, name, cinema_id, seats, created_at, updated_at) values ('e88a0781-57bd-46eb-9e2a-f2930d41cd86', 'Salle 3 - Pathe', '33a3e254-349a-4e8a-ab75-a9ac0d44e3b3', 80, '2024-01-01 11:11:11.111', '2024-01-01 11:11:11.111');
insert into room (uid, name, cinema_id, seats, created_at, updated_at) values ('8b847b8d-e64d-4b5a-83da-eeb70950f604', 'Salle 4 - Pathe', '33a3e254-349a-4e8a-ab75-a9ac0d44e3b3', 60, '2024-01-01 11:11:11.111', '2024-01-01 11:11:11.111');
insert into room (uid, name, cinema_id, seats, created_at, updated_at) values ('670817b1-513e-4f98-9ece-be4402fcc1eb', 'Salle IMAX - Pathe', '33a3e254-349a-4e8a-ab75-a9ac0d44e3b3', 360, '2024-01-01 11:11:11.111', '2024-01-01 11:11:11.111');
insert into room (uid, name, cinema_id, seats, created_at, updated_at) values ('9711c3ec-09fc-4c85-8b18-b870fa9cc88f', 'Salle VIP - Pathe', '33a3e254-349a-4e8a-ab75-a9ac0d44e3b3', 10, '2024-01-01 11:11:11.111', '2024-01-01 11:11:11.111');

-- Salle 1 - Cezanne
insert into seance (uid, room_id, movie, date, created_at, updated_at) values ('ecbbdd2d-3f1d-40c8-bfd0-f166c24034f8', '5fee4c75-a06a-4d4c-b65b-d3d23958cc85', '55a2e254-349a-4e8a-ab75-a9ac0d44e7e2', '2024-12-01 11:11:11.111', '2024-01-01 11:11:11.111', '2024-01-01 11:11:11.111');
insert into seance (uid, room_id, movie, date, created_at, updated_at) values ('e820bdd4-0358-46af-bc11-4716ac25235e', '5fee4c75-a06a-4d4c-b65b-d3d23958cc85', '11630174-0c00-41fa-9867-9fb0e08ebf53', '2024-12-01 15:11:11.111', '2024-01-01 11:11:11.111', '2024-01-01 11:11:11.111');
insert into seance (uid, room_id, movie, date, created_at, updated_at) values ('5b9b7ca6-c3ce-46f7-a3ab-4da073c158f0', '5fee4c75-a06a-4d4c-b65b-d3d23958cc85', '55a2e254-349a-4e8a-ab75-a9ac0d44e7e2', '2024-12-01 18:11:11.111', '2024-01-01 11:11:11.111', '2024-01-01 11:11:11.111');

-- Salle VIP - Pathe
insert into seance (uid, room_id, movie, date, created_at, updated_at) values ('a22d78f0-3661-40e4-8d09-12b822dd63ce', '9711c3ec-09fc-4c85-8b18-b870fa9cc88f', '55a2e254-349a-4e8a-ab75-a9ac0d44e7e2', '2024-12-01 11:11:11.111', '2024-01-01 11:11:11.111', '2024-01-01 11:11:11.111');
insert into seance (uid, room_id, movie, date, created_at, updated_at) values ('0936691a-c4b6-4390-8e5b-4c8b7b6487f7', '9711c3ec-09fc-4c85-8b18-b870fa9cc88f', '55a2e254-349a-4e8a-ab75-a9ac0d44e7e2', '2024-12-01 15:11:11.111', '2024-01-01 11:11:11.111', '2024-01-01 11:11:11.111');

insert into reservation(uid, seance_id, seats, rank, status, expires_at, created_at, updated_at) values ('0da315ff-b24b-499d-808d-fc6c9607c860', 'a22d78f0-3661-40e4-8d09-12b822dd63ce', 5, 0, 'open', NOW() + interval '15 MINUTES', '2024-01-01 11:11:11.111', '2024-01-01 11:11:11.111');
insert into reservation(uid, seance_id, seats, rank, status, expires_at, created_at, updated_at) values ('b1c06c95-a8f5-4afb-b3e4-84468bd1f4ad', 'a22d78f0-3661-40e4-8d09-12b822dd63ce', 3, 1, 'open', NOW() + interval '15 MINUTES', '2024-01-01 11:11:11.111', '2024-01-01 11:11:11.111');