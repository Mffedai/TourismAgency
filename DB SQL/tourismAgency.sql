PGDMP  0    #                |            tourismAgency    16.1    16.1 "               0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    24955    tourismAgency    DATABASE     �   CREATE DATABASE "tourismAgency" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_Australia.1252';
    DROP DATABASE "tourismAgency";
                admin    false            �            1259    24965    otel    TABLE     �  CREATE TABLE public.otel (
    otel_id bigint NOT NULL,
    otel_name text NOT NULL,
    otel_city text NOT NULL,
    otel_region text NOT NULL,
    otel_address text NOT NULL,
    otel_email text NOT NULL,
    otel_phone text NOT NULL,
    otel_rate text NOT NULL,
    otel_otopark boolean NOT NULL,
    otel_wifi boolean NOT NULL,
    otel_pool boolean NOT NULL,
    otel_fitness boolean NOT NULL,
    otel_concierge boolean NOT NULL,
    otel_spa boolean NOT NULL,
    otel_service boolean NOT NULL
);
    DROP TABLE public.otel;
       public         heap    admin    false            �            1259    24964    admin_role_otel_id_seq    SEQUENCE     �   ALTER TABLE public.otel ALTER COLUMN otel_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.admin_role_otel_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          admin    false    218            �            1259    24981    pension    TABLE     �   CREATE TABLE public.pension (
    pension_id bigint NOT NULL,
    pension_otel_id bigint NOT NULL,
    pension_name text NOT NULL
);
    DROP TABLE public.pension;
       public         heap    admin    false            �            1259    24980    pension_pension_id_seq    SEQUENCE     �   ALTER TABLE public.pension ALTER COLUMN pension_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.pension_pension_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          admin    false    222            �            1259    24995    reservation    TABLE     �  CREATE TABLE public.reservation (
    reservation_id bigint NOT NULL,
    room_id bigint NOT NULL,
    book_name text NOT NULL,
    book_email text NOT NULL,
    book_phone text NOT NULL,
    guest_name text NOT NULL,
    guest_tc text NOT NULL,
    total_price bigint NOT NULL,
    entry_date date NOT NULL,
    exit_date date NOT NULL,
    adult_number bigint NOT NULL,
    child_number bigint NOT NULL
);
    DROP TABLE public.reservation;
       public         heap    admin    false            �            1259    24994    reservation_reservation_id_seq    SEQUENCE     �   ALTER TABLE public.reservation ALTER COLUMN reservation_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.reservation_reservation_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          admin    false    226            �            1259    24989    room    TABLE       CREATE TABLE public.room (
    room_id bigint NOT NULL,
    room_otel_id bigint NOT NULL,
    room_season_id bigint NOT NULL,
    room_pension_id bigint NOT NULL,
    room_stok bigint NOT NULL,
    room_bed bigint NOT NULL,
    room_mtrsqr bigint NOT NULL,
    room_prc_adult bigint NOT NULL,
    room_prc_child bigint NOT NULL,
    room_aircndtn boolean NOT NULL,
    room_minibar boolean NOT NULL,
    room_tv boolean NOT NULL,
    room_safe boolean NOT NULL,
    room_fridge boolean NOT NULL,
    room_type text NOT NULL
);
    DROP TABLE public.room;
       public         heap    admin    false            �            1259    24988    room_room_id_seq    SEQUENCE     �   ALTER TABLE public.room ALTER COLUMN room_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.room_room_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          admin    false    224            �            1259    24973    season    TABLE     �   CREATE TABLE public.season (
    season_id bigint NOT NULL,
    season_otel_id bigint NOT NULL,
    strt_date date NOT NULL,
    fnsh_date date NOT NULL
);
    DROP TABLE public.season;
       public         heap    admin    false            �            1259    24972    season_season_id_seq    SEQUENCE     �   ALTER TABLE public.season ALTER COLUMN season_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.season_season_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          admin    false    220            �            1259    24957    user    TABLE     �   CREATE TABLE public."user" (
    user_id bigint NOT NULL,
    user_name text NOT NULL,
    user_pass text NOT NULL,
    user_role text NOT NULL
);
    DROP TABLE public."user";
       public         heap    admin    false            �            1259    24956    user_user_id_seq    SEQUENCE     �   ALTER TABLE public."user" ALTER COLUMN user_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.user_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          admin    false    216                      0    24965    otel 
   TABLE DATA           �   COPY public.otel (otel_id, otel_name, otel_city, otel_region, otel_address, otel_email, otel_phone, otel_rate, otel_otopark, otel_wifi, otel_pool, otel_fitness, otel_concierge, otel_spa, otel_service) FROM stdin;
    public          admin    false    218   x)                 0    24981    pension 
   TABLE DATA           L   COPY public.pension (pension_id, pension_otel_id, pension_name) FROM stdin;
    public          admin    false    222   D.                 0    24995    reservation 
   TABLE DATA           �   COPY public.reservation (reservation_id, room_id, book_name, book_email, book_phone, guest_name, guest_tc, total_price, entry_date, exit_date, adult_number, child_number) FROM stdin;
    public          admin    false    226   �/                 0    24989    room 
   TABLE DATA           �   COPY public.room (room_id, room_otel_id, room_season_id, room_pension_id, room_stok, room_bed, room_mtrsqr, room_prc_adult, room_prc_child, room_aircndtn, room_minibar, room_tv, room_safe, room_fridge, room_type) FROM stdin;
    public          admin    false    224   9       	          0    24973    season 
   TABLE DATA           Q   COPY public.season (season_id, season_otel_id, strt_date, fnsh_date) FROM stdin;
    public          admin    false    220   >                 0    24957    user 
   TABLE DATA           J   COPY public."user" (user_id, user_name, user_pass, user_role) FROM stdin;
    public          admin    false    216   :?                  0    0    admin_role_otel_id_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.admin_role_otel_id_seq', 32, true);
          public          admin    false    217                       0    0    pension_pension_id_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public.pension_pension_id_seq', 131, true);
          public          admin    false    221                       0    0    reservation_reservation_id_seq    SEQUENCE SET     M   SELECT pg_catalog.setval('public.reservation_reservation_id_seq', 70, true);
          public          admin    false    225                       0    0    room_room_id_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.room_room_id_seq', 116, true);
          public          admin    false    223                       0    0    season_season_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.season_season_id_seq', 55, true);
          public          admin    false    219                       0    0    user_user_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.user_user_id_seq', 17, true);
          public          admin    false    215            l           2606    24971    otel admin_role_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.otel
    ADD CONSTRAINT admin_role_pkey PRIMARY KEY (otel_id);
 >   ALTER TABLE ONLY public.otel DROP CONSTRAINT admin_role_pkey;
       public            admin    false    218            p           2606    24987    pension pension_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.pension
    ADD CONSTRAINT pension_pkey PRIMARY KEY (pension_id);
 >   ALTER TABLE ONLY public.pension DROP CONSTRAINT pension_pkey;
       public            admin    false    222            t           2606    25001    reservation reservation_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT reservation_pkey PRIMARY KEY (reservation_id);
 F   ALTER TABLE ONLY public.reservation DROP CONSTRAINT reservation_pkey;
       public            admin    false    226            r           2606    24993    room room_pkey 
   CONSTRAINT     Q   ALTER TABLE ONLY public.room
    ADD CONSTRAINT room_pkey PRIMARY KEY (room_id);
 8   ALTER TABLE ONLY public.room DROP CONSTRAINT room_pkey;
       public            admin    false    224            n           2606    24979    season season_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.season
    ADD CONSTRAINT season_pkey PRIMARY KEY (season_id);
 <   ALTER TABLE ONLY public.season DROP CONSTRAINT season_pkey;
       public            admin    false    220            j           2606    24963    user user_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (user_id);
 :   ALTER TABLE ONLY public."user" DROP CONSTRAINT user_pkey;
       public            admin    false    216               �  x�mU�n�8]_��&�H=wJ�vZL^��	:�%Qc�7 )���ϕ�W1#rl<<�<n�S���^}��hW�Z����yv;�ZYv��j�۶�+\��T�.+�S%�s�"���_��߽z�s��܍M�+畅{���Z�y��A� �x���mZ���J�lzid+MRT�E�ʴڨ=�.X̳L����!w���Jz�C�u���N�z	�<�ׄuc�ZA�)�T^��m�.��DB�Q�H5'bM�3�5�x�N�Z�5=���� `7�ݢU��\)�VfSa"f<�/XFIe��q<�W����?�q���i�p����?���-:G��.����h�v�g�,$���� L'�̄RX~h�\ٵ���he��y���IiSˮg��*�z�Պ���R��%�>�`[J������@��;B����2k��&͒4fϣ)-��J�ܻ��-��gt�K�ȳ��?I�"�Y��(��e���'v�YZ~�(��Q�u���g�(�/X�,Jӓ�N����[�p�I;9��{і�I��(B�촲��W��˵vAQ�ض��@"�.X�$���>�x:��"�R�����Z���+�″?,%�bR�U�q�������1��,KO>Kx����J`�$���;����R��x�~x��୲�hEэ�e�k2qNaLb����=}9�x_ )\��-��E�t��yL$�ZZ��dKe'6`��(�Ҋ2��J����pj�8�����f�@HI��ޑ�<��Dߠ�DY�����j�jE�����^u�]ͼ�d���&� ���(S`�}M��VY���
+Q ����R�J����ޱ�R�g��𧠄�SRU���$��I��F�����+c� ��w-#{9����־T�G�����PӀ`�x��X #�z�n]�vC�ά�AGY��+��(=���NI�����:���TY�Gy"����;Z;�iޥ��Y�)�Q�#��(S.�s��[Z�{X�t4UQ�ZU�;�Az�B�8�G�5p�2�<n�%��R�7�����J�Ώ�
?�it�On ��I�g�;l̞���׌|Ǟ��i�)��1GU���U��
��fQu{B���9yn^s6<�7����X*x��w�V}M0U�iUJ����TM�v��u3*�p�~ֿ��۹R1�ULW?�an�\.�G>r         s  x�}�[K�@��'?Fvf���E��7!�6��5�^D�����d�c&s9��I�[���~�V�t�}����%���g�ܷ�Ǧ9WW����zٮW/t݂��#��ت�����Y�_���tι۷�ݹ�'�	��h5�IdKo�o�T��$%��K�v}W���Rb}E���7��H�$�I��d��v��
=6qsQp�Dl�E.�n���s ~|0_� ���Ɛ�X���l�{f76��t��d?6 5�U�N�����!�j]fPL�2Pf0,>jb Χ�n���d:Tr�eϔ�����ߤU&��f�����U'P�.h��7���\��YA>Oz���A9���]8�>F�2�� �z��MQ&��         @	  x��Xے�6}��
� U�x�=N9�o��T\[�/IQ�x�yE������= )i�Y��x�5n�!ݧ�Ko��o��M�뻱�����j�i����8m�%��03eݺ��L�U���IB<�<N\����H{P���7]Q����0��<P��ZǴ�S�U��1��,��e%Z	�9�D/@�W�Ŕ��xP@c��g�PNѧ�,�3Uǰ~O�z��?7]9�4"v����՝���(�d?��}̆�l��}6��Hm�CZ�Ꟊq3+c��^��&Z�(d�]���1�q��2�5kB��Ӿ��iʐ)-El,ǝ{��H�'&�]�p)���/�zh���h�.Ɣ��!��m��g�������̒��$yP�RUV����e-��a��m�W�_j�9�c�����-���v�&����oI;$�*�)����fjۋ����y��K��176VF�e��Z�q�^B�	F�>�c>�G���8�yedK)c��
��p�C\��FG�_ٌ>�Cݠ��f��CM�x1�$=e��_L���'���,� \w�2(	�����a��a�-��p�a�engnF*����q{�C���*R�E��;��	~7<��݋�W6y�e�S�nk*�t<���d�f>�u6�VFZvq&���&f<�ĥ|��Hz�uѻ�.G�a�<v�i\�K��	)��ބ��J�&�e�\A��_Ғ*M��_�=}���'��m7�K'Ga�Ӊ[}	������H�+ �ȸ!��SYf]}(���~&��n�+�b�v�b�vI�S��?�6Z0P-W76F��ʹH΂�u�i��2z�Ʒ�1_��o��CʥȤ�1�k�
����Ш-�I��`�L�@���ŀ��N>%�V�ֻ���Δ:f�6�%8���4��7�?�.EBF��~�=G_�q�v�x�5�����٢�+�0a~��@]8�y�����`b*_�~`vMv�"�
���cYp�ͷ#2��22VH��OJc�$ ݙ��Ľ���M��i�_-���H��u���o�A�f 3b�� ���)����:�>����˰]-�z� �໵���V����
��V':�/L�$���Ԕm�K��S^��@R���V��r��]q���<����IA�����ڟF�R��M�KS�aFٰ�OHk<��F�8��tU7sC0W��7�bn|#v噲CX]:N����<bJ�G!:L��N�����xͮ1&����*c�OS+���n?z�T�X�O.H���/#�
Ʃ�Q,��>��W*p��n������c͵��9P�N |7��D�ȠY�-�Ӝ���q1��)n�.���k�t�9f/�]��E20%��
��&�<_I$o�7L�y�Z�����@�	j H�'��#\��([�=��Yb�۳G������j�i~Ƨ���=.c�aL9	� }�������cԡ6_��mjٯ�4�����٣�K2+���\_6F"���z|
�Jpv��h }���;�':a�0��>?���pH"���f�-d%É�OQ�V<ⓡ�?5s۠s��b�Qݡ,�nAA�H�K��nA�PԖ�|P�'�M�8^Y|��M���X0�4��م���K���ݳ��e'�W���Gd���
�[����&��CE�$a�b����ƀra����ʾ�\A<�=f������qH�خ���C2i�F ôG �TY�:�%�=���}�R��m�����^��r�
M���QZ�q�w�ݞw0E��]W��B��ư���!Ûk�"� �-��Z��Ux����wQ£(�(���PG��g��H�y1�t�cV<�c����՘y�b��d�Џ�U�á1����m��^m�?��BJ�u��1$���� <uUx����54�5U쪢�?w�������u�����?
���J�i�vŸ+��~A+����U����n�#�~~�O�l����X5���Uߣ�79���c�ku'P��V�8�/�vy����@?~֗ݟ5�j���`���q\p���=<qLC�$?`蛹H'<� Ҟ��	*��E�}7�b�t�U~vm�x�Xl���T��F�ׁ��(�ـ�`�����?:
q�F*w�|����Q�Y��K�����L(sl��?n`�[��O�W8�Ւ)B5#&�2�$`��\6A�p0
ξ,G��\o��[��7�s�iG��/
O�v}�A�C�����$����܂�[pq�@���>x��<{aF�!�:���/����9�"���#��	A�seB��O�2��{<���mX-�7`u�2���W�og�Xԋ��0���_�������3         �  x�uW�n$7<��z�S��kNҁ�d������h����X,)�h�oMv�?�G�G�֐��K.�����__�~�`(O&{C�dCIN�bi������]q��_�=�Ў�a8 ��s�j�����S�d<��?.ōI���[�8��?���5��5��_N�@�"Z�_����G!n ��`\��i�)HR���mN��΀���|��[�D�i"�&��R���FH�
�8˿��z6�I!�5w��P�^�!�ϒL����u�<2VR�q�!��d?�Dr�P?k�����\��ň�~R6(��-�g`�KL��"���=e�<��ZI΄�m��c\O!D�ܥ��{Ӆ�*�]�1y�-��;�?��n��ړB��U��^�'�H����Y��x.ʞ|Z��p�<�#6����B<�6'�D و�쵙i�
�����nҼ�-Z���t����׮����ƒ��lh�ɝ�5%�YFA�䖤sn��@#�+�8!̡N)Š�*���K�^G��Y[�f|��v�RUHD���0*�zs�d��4b�2��:�y�U�Au�뷡^G�X� �a�wG`T �P����T�L= �b�"'���8�f��3Da�/�JN��!���1X��Ĺ���Y�h�L���e��s9�7�F�XB7e�eH.:��71���œWY����O^7����ٓ��	�qhBG}F�<׵8�{Rb�ô�<��������(w��:�E�-��,�$�	�m��M㺙���-�Ԧ�<�<��a�����4����w�Xm�%�����v�V��A���	����ɖ7�)�ً�KuM�cܶ%�������k.-Xc� ���qr�w��I�D���G��Ĺ�F��mF<��1��Y?�֎�E'A޶�y��^�kAi��컓Q��J�7�-��8ź�����7�=0�����hA!d���������HQ8��-�S�E�J��"%�5����"I� ���3�XTq47��#�ťAn&Fօ��3#�^UTM��G>�;�p������恓kI������B�"@�$�w�[Ua)m=�uٸ��KMB��d@�,X���2���h���W�yd��и�@�/���g���OFۮRҋ� ��;N�E֥8���M���9j���E��0E�V�ֵF�aF�>�\l۟/���s����yA��䅺�^�u�=5g�G��$���J�"J]�+�^�?~����.ֺ      	   "  x�MRѕ� ��]���r��q`��5&!�І�����e�8�l�hu0:r�2(H�:�W&���i�.�T�Xa�9��T�{��MwyH��Ԟ5�����4�xPR�y0=�3���~35��L&��=5���C&�]c�7��| C�]���~�MF�r�ыy�+1dv=�of���ɽ��[��|?�^��v���7hp���F��o�����^CdP������^Ƌ��Fxז��9_y`�Evǜd�)�}�;����~�%V2��Dj�3S<Ю쬕L�H�4���+�~���冿         9   x�3�LJ!W� �HWW.C��Drt����24�tK,��PpKMI�����qqq ���     