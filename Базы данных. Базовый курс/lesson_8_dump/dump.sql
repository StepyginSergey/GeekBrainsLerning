PGDMP         ,    
            w            test    11.2    11.2 &    &           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false            '           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false            (           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                       false            )           1262    16393    test    DATABASE     �   CREATE DATABASE test WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Russian_Russia.1251' LC_CTYPE = 'Russian_Russia.1251';
    DROP DATABASE test;
             postgres    false                        2615    16395    test    SCHEMA        CREATE SCHEMA test;
    DROP SCHEMA test;
             postgres    false            �            1259    16430    content    TABLE     �   CREATE TABLE test.content (
    id integer NOT NULL,
    user_id integer NOT NULL,
    content_type_id integer NOT NULL,
    content_data character varying(250) NOT NULL
);
    DROP TABLE test.content;
       test         postgres    false    7            �            1259    16428    content_id_seq    SEQUENCE     �   CREATE SEQUENCE test.content_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE test.content_id_seq;
       test       postgres    false    7    202            *           0    0    content_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE test.content_id_seq OWNED BY test.content.id;
            test       postgres    false    201            �            1259    16406    content_type    TABLE     f   CREATE TABLE test.content_type (
    id integer NOT NULL,
    name character varying(250) NOT NULL
);
    DROP TABLE test.content_type;
       test         postgres    false    7            �            1259    16404    content_type_id_seq    SEQUENCE     �   CREATE SEQUENCE test.content_type_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE test.content_type_id_seq;
       test       postgres    false    198    7            +           0    0    content_type_id_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE test.content_type_id_seq OWNED BY test.content_type.id;
            test       postgres    false    197            �            1259    16454    likes    TABLE     �   CREATE TABLE test.likes (
    id integer NOT NULL,
    user_id_put integer NOT NULL,
    user_id_to integer NOT NULL,
    content_id integer NOT NULL,
    like_received boolean DEFAULT false,
    recall boolean DEFAULT false
);
    DROP TABLE test.likes;
       test         postgres    false    7            �            1259    16452    likes_id_seq    SEQUENCE     �   CREATE SEQUENCE test.likes_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 !   DROP SEQUENCE test.likes_id_seq;
       test       postgres    false    204    7            ,           0    0    likes_id_seq    SEQUENCE OWNED BY     9   ALTER SEQUENCE test.likes_id_seq OWNED BY test.likes.id;
            test       postgres    false    203            �            1259    16422    users    TABLE     �   CREATE TABLE test.users (
    id integer NOT NULL,
    name character varying(100) NOT NULL,
    lastname character varying(100) NOT NULL
);
    DROP TABLE test.users;
       test         postgres    false    7            �            1259    16420    users_id_seq    SEQUENCE     �   CREATE SEQUENCE test.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 !   DROP SEQUENCE test.users_id_seq;
       test       postgres    false    7    200            -           0    0    users_id_seq    SEQUENCE OWNED BY     9   ALTER SEQUENCE test.users_id_seq OWNED BY test.users.id;
            test       postgres    false    199            �
           2604    16433 
   content id    DEFAULT     d   ALTER TABLE ONLY test.content ALTER COLUMN id SET DEFAULT nextval('test.content_id_seq'::regclass);
 7   ALTER TABLE test.content ALTER COLUMN id DROP DEFAULT;
       test       postgres    false    201    202    202            �
           2604    16409    content_type id    DEFAULT     n   ALTER TABLE ONLY test.content_type ALTER COLUMN id SET DEFAULT nextval('test.content_type_id_seq'::regclass);
 <   ALTER TABLE test.content_type ALTER COLUMN id DROP DEFAULT;
       test       postgres    false    198    197    198            �
           2604    16457    likes id    DEFAULT     `   ALTER TABLE ONLY test.likes ALTER COLUMN id SET DEFAULT nextval('test.likes_id_seq'::regclass);
 5   ALTER TABLE test.likes ALTER COLUMN id DROP DEFAULT;
       test       postgres    false    203    204    204            �
           2604    16425    users id    DEFAULT     `   ALTER TABLE ONLY test.users ALTER COLUMN id SET DEFAULT nextval('test.users_id_seq'::regclass);
 5   ALTER TABLE test.users ALTER COLUMN id DROP DEFAULT;
       test       postgres    false    199    200    200            !          0    16430    content 
   TABLE DATA               K   COPY test.content (id, user_id, content_type_id, content_data) FROM stdin;
    test       postgres    false    202   �'                 0    16406    content_type 
   TABLE DATA               .   COPY test.content_type (id, name) FROM stdin;
    test       postgres    false    198   �(       #          0    16454    likes 
   TABLE DATA               ]   COPY test.likes (id, user_id_put, user_id_to, content_id, like_received, recall) FROM stdin;
    test       postgres    false    204   )                 0    16422    users 
   TABLE DATA               1   COPY test.users (id, name, lastname) FROM stdin;
    test       postgres    false    200   �)       .           0    0    content_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('test.content_id_seq', 36, true);
            test       postgres    false    201            /           0    0    content_type_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('test.content_type_id_seq', 4, true);
            test       postgres    false    197            0           0    0    likes_id_seq    SEQUENCE SET     9   SELECT pg_catalog.setval('test.likes_id_seq', 24, true);
            test       postgres    false    203            1           0    0    users_id_seq    SEQUENCE SET     8   SELECT pg_catalog.setval('test.users_id_seq', 6, true);
            test       postgres    false    199            �
           2606    16435    content content_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY test.content
    ADD CONSTRAINT content_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY test.content DROP CONSTRAINT content_pkey;
       test         postgres    false    202            �
           2606    16411    content_type content_type_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY test.content_type
    ADD CONSTRAINT content_type_pkey PRIMARY KEY (id);
 F   ALTER TABLE ONLY test.content_type DROP CONSTRAINT content_type_pkey;
       test         postgres    false    198            �
           2606    16461    likes likes_pkey 
   CONSTRAINT     L   ALTER TABLE ONLY test.likes
    ADD CONSTRAINT likes_pkey PRIMARY KEY (id);
 8   ALTER TABLE ONLY test.likes DROP CONSTRAINT likes_pkey;
       test         postgres    false    204            �
           2606    16427    users users_pkey 
   CONSTRAINT     L   ALTER TABLE ONLY test.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);
 8   ALTER TABLE ONLY test.users DROP CONSTRAINT users_pkey;
       test         postgres    false    200            �
           2606    16441    content content_type_id    FK CONSTRAINT     �   ALTER TABLE ONLY test.content
    ADD CONSTRAINT content_type_id FOREIGN KEY (content_type_id) REFERENCES test.content_type(id) ON UPDATE CASCADE ON DELETE RESTRICT;
 ?   ALTER TABLE ONLY test.content DROP CONSTRAINT content_type_id;
       test       postgres    false    202    2711    198            �
           2606    16436    content content_user_id    FK CONSTRAINT     �   ALTER TABLE ONLY test.content
    ADD CONSTRAINT content_user_id FOREIGN KEY (user_id) REFERENCES test.users(id) ON UPDATE CASCADE ON DELETE RESTRICT;
 ?   ALTER TABLE ONLY test.content DROP CONSTRAINT content_user_id;
       test       postgres    false    202    2713    200            �
           2606    16462    likes likes_content_id    FK CONSTRAINT     �   ALTER TABLE ONLY test.likes
    ADD CONSTRAINT likes_content_id FOREIGN KEY (content_id) REFERENCES test.content(id) ON UPDATE CASCADE ON DELETE RESTRICT;
 >   ALTER TABLE ONLY test.likes DROP CONSTRAINT likes_content_id;
       test       postgres    false    204    202    2715            �
           2606    16467    likes likes_user_id_put    FK CONSTRAINT     �   ALTER TABLE ONLY test.likes
    ADD CONSTRAINT likes_user_id_put FOREIGN KEY (user_id_put) REFERENCES test.users(id) ON UPDATE CASCADE ON DELETE RESTRICT;
 ?   ALTER TABLE ONLY test.likes DROP CONSTRAINT likes_user_id_put;
       test       postgres    false    200    204    2713            �
           2606    16472    likes likes_user_id_to    FK CONSTRAINT     �   ALTER TABLE ONLY test.likes
    ADD CONSTRAINT likes_user_id_to FOREIGN KEY (user_id_to) REFERENCES test.users(id) ON UPDATE CASCADE ON DELETE RESTRICT;
 >   ALTER TABLE ONLY test.likes DROP CONSTRAINT likes_user_id_to;
       test       postgres    false    200    2713    204            !   �   x�M�1��0k�1���d�/i��qp��Aޟuan�fG<����A�ݟ����II��7	�S�����>$��p�b�Vb�b��㸲jgiy���+��8dܚ�*.2�Vk�ډ\EϨ" ��(TM�Y�^�AVq��U\d�ZiU�Iϰqd}I�P1��[�����w� ��o�         \   x���	�@�sR����(���uqEM���a�7)��G���{��k%XH/�%G�qi-Xa�N�> ���`�~�۴-T�-{9�      #   }   x�=���0�35L��o��;A�Gm�ɍ���t��Y0��<���/�
N4!��#�0���'sa^%)�U�	���\x6z%4�q�y��Ր���|"�¸���*� Na�\��O}?f���2         -   x���   ��n$b��)��L&��b��l;���r}I�x��     