--
-- PostgreSQL database dump
--

-- Dumped from database version 15.1
-- Dumped by pg_dump version 15.1

-- Started on 2022-12-23 23:01:36

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 6 (class 2615 OID 16414)
-- Name: databaseprog; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA databaseprog;


ALTER SCHEMA databaseprog OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 221 (class 1259 OID 16464)
-- Name: commento; Type: TABLE; Schema: databaseprog; Owner: postgres
--

CREATE TABLE databaseprog.commento (
    id integer NOT NULL,
    contenuto character varying NOT NULL,
    numero_mi_piace integer NOT NULL,
    numero_non_mi_piace integer NOT NULL,
    recensione integer NOT NULL,
    utente character varying(50) NOT NULL
);


ALTER TABLE databaseprog.commento OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 16463)
-- Name: commento_id_seq; Type: SEQUENCE; Schema: databaseprog; Owner: postgres
--

CREATE SEQUENCE databaseprog.commento_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE databaseprog.commento_id_seq OWNER TO postgres;

--
-- TOC entry 3394 (class 0 OID 0)
-- Dependencies: 220
-- Name: commento_id_seq; Type: SEQUENCE OWNED BY; Schema: databaseprog; Owner: postgres
--

ALTER SEQUENCE databaseprog.commento_id_seq OWNED BY databaseprog.commento.id;


--
-- TOC entry 224 (class 1259 OID 16514)
-- Name: feedback_commenti; Type: TABLE; Schema: databaseprog; Owner: postgres
--

CREATE TABLE databaseprog.feedback_commenti (
    utente character varying(50) NOT NULL,
    commento integer NOT NULL,
    tipo boolean NOT NULL
);


ALTER TABLE databaseprog.feedback_commenti OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 16499)
-- Name: feedback_recensione; Type: TABLE; Schema: databaseprog; Owner: postgres
--

CREATE TABLE databaseprog.feedback_recensione (
    utente character varying(50) NOT NULL,
    recensione integer NOT NULL,
    tipo boolean NOT NULL
);


ALTER TABLE databaseprog.feedback_recensione OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 16422)
-- Name: gioco; Type: TABLE; Schema: databaseprog; Owner: postgres
--

CREATE TABLE databaseprog.gioco (
    id integer NOT NULL
);


ALTER TABLE databaseprog.gioco OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 16443)
-- Name: recensione; Type: TABLE; Schema: databaseprog; Owner: postgres
--

CREATE TABLE databaseprog.recensione (
    id integer NOT NULL,
    titolo character varying(50) NOT NULL,
    contenuto character varying NOT NULL,
    voto integer NOT NULL,
    numero_mi_piace integer NOT NULL,
    numero_non_mi_piace integer NOT NULL,
    utente character varying(50) NOT NULL,
    gioco integer NOT NULL
);


ALTER TABLE databaseprog.recensione OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 16442)
-- Name: recensione_id_seq; Type: SEQUENCE; Schema: databaseprog; Owner: postgres
--

CREATE SEQUENCE databaseprog.recensione_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE databaseprog.recensione_id_seq OWNER TO postgres;

--
-- TOC entry 3395 (class 0 OID 0)
-- Dependencies: 218
-- Name: recensione_id_seq; Type: SEQUENCE OWNED BY; Schema: databaseprog; Owner: postgres
--

ALTER SEQUENCE databaseprog.recensione_id_seq OWNED BY databaseprog.recensione.id;


--
-- TOC entry 222 (class 1259 OID 16482)
-- Name: segnalazione; Type: TABLE; Schema: databaseprog; Owner: postgres
--

CREATE TABLE databaseprog.segnalazione (
    recensione integer NOT NULL,
    utente character varying(50) NOT NULL,
    motivazione character varying NOT NULL
);


ALTER TABLE databaseprog.segnalazione OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 16415)
-- Name: utente; Type: TABLE; Schema: databaseprog; Owner: postgres
--

CREATE TABLE databaseprog.utente (
    username character varying(50) NOT NULL,
    email character varying(50) NOT NULL,
    password character varying(50) NOT NULL,
    amministratore boolean NOT NULL,
    bandito boolean NOT NULL
);


ALTER TABLE databaseprog.utente OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 16427)
-- Name: wishlist; Type: TABLE; Schema: databaseprog; Owner: postgres
--

CREATE TABLE databaseprog.wishlist (
    utente character varying(50) NOT NULL,
    gioco integer NOT NULL
);


ALTER TABLE databaseprog.wishlist OWNER TO postgres;

--
-- TOC entry 3204 (class 2604 OID 16467)
-- Name: commento id; Type: DEFAULT; Schema: databaseprog; Owner: postgres
--

ALTER TABLE ONLY databaseprog.commento ALTER COLUMN id SET DEFAULT nextval('databaseprog.commento_id_seq'::regclass);


--
-- TOC entry 3203 (class 2604 OID 16446)
-- Name: recensione id; Type: DEFAULT; Schema: databaseprog; Owner: postgres
--

ALTER TABLE ONLY databaseprog.recensione ALTER COLUMN id SET DEFAULT nextval('databaseprog.recensione_id_seq'::regclass);


--
-- TOC entry 3385 (class 0 OID 16464)
-- Dependencies: 221
-- Data for Name: commento; Type: TABLE DATA; Schema: databaseprog; Owner: postgres
--

COPY databaseprog.commento (id, contenuto, numero_mi_piace, numero_non_mi_piace, recensione, utente) FROM stdin;
2	bukkin	10	2	2	Pie_Oxx
1	forzaNapoli	10	2	2	Pie_Oxx
\.


--
-- TOC entry 3388 (class 0 OID 16514)
-- Dependencies: 224
-- Data for Name: feedback_commenti; Type: TABLE DATA; Schema: databaseprog; Owner: postgres
--

COPY databaseprog.feedback_commenti (utente, commento, tipo) FROM stdin;
\.


--
-- TOC entry 3387 (class 0 OID 16499)
-- Dependencies: 223
-- Data for Name: feedback_recensione; Type: TABLE DATA; Schema: databaseprog; Owner: postgres
--

COPY databaseprog.feedback_recensione (utente, recensione, tipo) FROM stdin;
\.


--
-- TOC entry 3380 (class 0 OID 16422)
-- Dependencies: 216
-- Data for Name: gioco; Type: TABLE DATA; Schema: databaseprog; Owner: postgres
--

COPY databaseprog.gioco (id) FROM stdin;
11
\.


--
-- TOC entry 3383 (class 0 OID 16443)
-- Dependencies: 219
-- Data for Name: recensione; Type: TABLE DATA; Schema: databaseprog; Owner: postgres
--

COPY databaseprog.recensione (id, titolo, contenuto, voto, numero_mi_piace, numero_non_mi_piace, utente, gioco) FROM stdin;
2	aaaaa	Bellissimoooo	100	50	20	Pie_Oxx	11
\.


--
-- TOC entry 3386 (class 0 OID 16482)
-- Dependencies: 222
-- Data for Name: segnalazione; Type: TABLE DATA; Schema: databaseprog; Owner: postgres
--

COPY databaseprog.segnalazione (recensione, utente, motivazione) FROM stdin;
\.


--
-- TOC entry 3379 (class 0 OID 16415)
-- Dependencies: 215
-- Data for Name: utente; Type: TABLE DATA; Schema: databaseprog; Owner: postgres
--

COPY databaseprog.utente (username, email, password, amministratore, bandito) FROM stdin;
Pie_Oxx	piero.stalteri@gmail.comm	123456	t	f
\.


--
-- TOC entry 3381 (class 0 OID 16427)
-- Dependencies: 217
-- Data for Name: wishlist; Type: TABLE DATA; Schema: databaseprog; Owner: postgres
--

COPY databaseprog.wishlist (utente, gioco) FROM stdin;
\.


--
-- TOC entry 3396 (class 0 OID 0)
-- Dependencies: 220
-- Name: commento_id_seq; Type: SEQUENCE SET; Schema: databaseprog; Owner: postgres
--

SELECT pg_catalog.setval('databaseprog.commento_id_seq', 2, true);


--
-- TOC entry 3397 (class 0 OID 0)
-- Dependencies: 218
-- Name: recensione_id_seq; Type: SEQUENCE SET; Schema: databaseprog; Owner: postgres
--

SELECT pg_catalog.setval('databaseprog.recensione_id_seq', 2, true);


--
-- TOC entry 3218 (class 2606 OID 16471)
-- Name: commento commento_pk; Type: CONSTRAINT; Schema: databaseprog; Owner: postgres
--

ALTER TABLE ONLY databaseprog.commento
    ADD CONSTRAINT commento_pk PRIMARY KEY (id);


--
-- TOC entry 3224 (class 2606 OID 16518)
-- Name: feedback_commenti feedback_commenti_pk; Type: CONSTRAINT; Schema: databaseprog; Owner: postgres
--

ALTER TABLE ONLY databaseprog.feedback_commenti
    ADD CONSTRAINT feedback_commenti_pk PRIMARY KEY (utente, commento);


--
-- TOC entry 3222 (class 2606 OID 16503)
-- Name: feedback_recensione feedback_recensione_pk; Type: CONSTRAINT; Schema: databaseprog; Owner: postgres
--

ALTER TABLE ONLY databaseprog.feedback_recensione
    ADD CONSTRAINT feedback_recensione_pk PRIMARY KEY (utente, recensione);


--
-- TOC entry 3210 (class 2606 OID 16426)
-- Name: gioco gioco_pk; Type: CONSTRAINT; Schema: databaseprog; Owner: postgres
--

ALTER TABLE ONLY databaseprog.gioco
    ADD CONSTRAINT gioco_pk PRIMARY KEY (id);


--
-- TOC entry 3214 (class 2606 OID 16450)
-- Name: recensione recensione_pk; Type: CONSTRAINT; Schema: databaseprog; Owner: postgres
--

ALTER TABLE ONLY databaseprog.recensione
    ADD CONSTRAINT recensione_pk PRIMARY KEY (id);


--
-- TOC entry 3216 (class 2606 OID 16462)
-- Name: recensione recensione_un; Type: CONSTRAINT; Schema: databaseprog; Owner: postgres
--

ALTER TABLE ONLY databaseprog.recensione
    ADD CONSTRAINT recensione_un UNIQUE (utente, gioco);


--
-- TOC entry 3220 (class 2606 OID 16488)
-- Name: segnalazione segnalazione_pk; Type: CONSTRAINT; Schema: databaseprog; Owner: postgres
--

ALTER TABLE ONLY databaseprog.segnalazione
    ADD CONSTRAINT segnalazione_pk PRIMARY KEY (recensione, utente);


--
-- TOC entry 3206 (class 2606 OID 16419)
-- Name: utente utente_pk; Type: CONSTRAINT; Schema: databaseprog; Owner: postgres
--

ALTER TABLE ONLY databaseprog.utente
    ADD CONSTRAINT utente_pk PRIMARY KEY (username);


--
-- TOC entry 3208 (class 2606 OID 16421)
-- Name: utente utente_un; Type: CONSTRAINT; Schema: databaseprog; Owner: postgres
--

ALTER TABLE ONLY databaseprog.utente
    ADD CONSTRAINT utente_un UNIQUE (email);


--
-- TOC entry 3212 (class 2606 OID 16431)
-- Name: wishlist wishlist_pk; Type: CONSTRAINT; Schema: databaseprog; Owner: postgres
--

ALTER TABLE ONLY databaseprog.wishlist
    ADD CONSTRAINT wishlist_pk PRIMARY KEY (utente, gioco);


--
-- TOC entry 3229 (class 2606 OID 16472)
-- Name: commento commento_fk_recensione; Type: FK CONSTRAINT; Schema: databaseprog; Owner: postgres
--

ALTER TABLE ONLY databaseprog.commento
    ADD CONSTRAINT commento_fk_recensione FOREIGN KEY (recensione) REFERENCES databaseprog.recensione(id);


--
-- TOC entry 3230 (class 2606 OID 16477)
-- Name: commento commento_fk_utente; Type: FK CONSTRAINT; Schema: databaseprog; Owner: postgres
--

ALTER TABLE ONLY databaseprog.commento
    ADD CONSTRAINT commento_fk_utente FOREIGN KEY (utente) REFERENCES databaseprog.utente(username);


--
-- TOC entry 3235 (class 2606 OID 16524)
-- Name: feedback_commenti feedback_commenti_fk_commento; Type: FK CONSTRAINT; Schema: databaseprog; Owner: postgres
--

ALTER TABLE ONLY databaseprog.feedback_commenti
    ADD CONSTRAINT feedback_commenti_fk_commento FOREIGN KEY (commento) REFERENCES databaseprog.commento(id);


--
-- TOC entry 3236 (class 2606 OID 16519)
-- Name: feedback_commenti feedback_commenti_fk_utente; Type: FK CONSTRAINT; Schema: databaseprog; Owner: postgres
--

ALTER TABLE ONLY databaseprog.feedback_commenti
    ADD CONSTRAINT feedback_commenti_fk_utente FOREIGN KEY (utente) REFERENCES databaseprog.utente(username);


--
-- TOC entry 3233 (class 2606 OID 16509)
-- Name: feedback_recensione feedback_recensione_fk_recensione; Type: FK CONSTRAINT; Schema: databaseprog; Owner: postgres
--

ALTER TABLE ONLY databaseprog.feedback_recensione
    ADD CONSTRAINT feedback_recensione_fk_recensione FOREIGN KEY (recensione) REFERENCES databaseprog.recensione(id);


--
-- TOC entry 3234 (class 2606 OID 16504)
-- Name: feedback_recensione feedback_recensione_fk_utente; Type: FK CONSTRAINT; Schema: databaseprog; Owner: postgres
--

ALTER TABLE ONLY databaseprog.feedback_recensione
    ADD CONSTRAINT feedback_recensione_fk_utente FOREIGN KEY (utente) REFERENCES databaseprog.utente(username);


--
-- TOC entry 3227 (class 2606 OID 16456)
-- Name: recensione recensione_fk_gioco; Type: FK CONSTRAINT; Schema: databaseprog; Owner: postgres
--

ALTER TABLE ONLY databaseprog.recensione
    ADD CONSTRAINT recensione_fk_gioco FOREIGN KEY (gioco) REFERENCES databaseprog.gioco(id);


--
-- TOC entry 3228 (class 2606 OID 16451)
-- Name: recensione recensione_fk_utente; Type: FK CONSTRAINT; Schema: databaseprog; Owner: postgres
--

ALTER TABLE ONLY databaseprog.recensione
    ADD CONSTRAINT recensione_fk_utente FOREIGN KEY (utente) REFERENCES databaseprog.utente(username);


--
-- TOC entry 3231 (class 2606 OID 16489)
-- Name: segnalazione segnalazione_fk_recensione; Type: FK CONSTRAINT; Schema: databaseprog; Owner: postgres
--

ALTER TABLE ONLY databaseprog.segnalazione
    ADD CONSTRAINT segnalazione_fk_recensione FOREIGN KEY (recensione) REFERENCES databaseprog.recensione(id);


--
-- TOC entry 3232 (class 2606 OID 16494)
-- Name: segnalazione segnalazione_fk_utente; Type: FK CONSTRAINT; Schema: databaseprog; Owner: postgres
--

ALTER TABLE ONLY databaseprog.segnalazione
    ADD CONSTRAINT segnalazione_fk_utente FOREIGN KEY (utente) REFERENCES databaseprog.utente(username);


--
-- TOC entry 3225 (class 2606 OID 16437)
-- Name: wishlist wishlist_fk_gioco; Type: FK CONSTRAINT; Schema: databaseprog; Owner: postgres
--

ALTER TABLE ONLY databaseprog.wishlist
    ADD CONSTRAINT wishlist_fk_gioco FOREIGN KEY (gioco) REFERENCES databaseprog.gioco(id);


--
-- TOC entry 3226 (class 2606 OID 16432)
-- Name: wishlist wishlist_fk_utente; Type: FK CONSTRAINT; Schema: databaseprog; Owner: postgres
--

ALTER TABLE ONLY databaseprog.wishlist
    ADD CONSTRAINT wishlist_fk_utente FOREIGN KEY (utente) REFERENCES databaseprog.utente(username);


-- Completed on 2022-12-23 23:01:36

--
-- PostgreSQL database dump complete
--

