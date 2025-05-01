-- public.companies definition

-- Drop table

-- DROP TABLE public.companies;

CREATE TABLE public.companies (
	id bigserial NOT NULL,
	country varchar(255) NOT NULL,
	"name" varchar(255) NOT NULL,
	number_of_employees varchar(255) NOT NULL,
	organization_sector varchar(255) NOT NULL,
	organization_type varchar(255) NOT NULL,
	CONSTRAINT companies_pkey PRIMARY KEY (id),
	CONSTRAINT uk_50ygfritln653mnfhxucoy8up UNIQUE (name)
);


-- public.form_definitions definition

-- Drop table

-- DROP TABLE public.form_definitions;

CREATE TABLE public.form_definitions (
	id bigserial NOT NULL,
	form_name varchar(255) NOT NULL,
	form_version varchar(255) NOT NULL,
	CONSTRAINT form_definitions_pkey PRIMARY KEY (id)
);


-- public.part_definitions definition

-- Drop table

-- DROP TABLE public.part_definitions;

CREATE TABLE public.part_definitions (
	id bigserial NOT NULL,
	part_name varchar(255) NOT NULL,
	part_number int4 NOT NULL,
	form_definition_id int8 NOT NULL,
	CONSTRAINT part_definitions_pkey PRIMARY KEY (id),
	CONSTRAINT fk4lkchmcwta9cdn0829otp96vb FOREIGN KEY (form_definition_id) REFERENCES public.form_definitions(id)
);


-- public.question_definitions definition

-- Drop table

-- DROP TABLE public.question_definitions;

CREATE TABLE public.question_definitions (
	id bigserial NOT NULL,
	question_number int4 NOT NULL,
	question_text varchar(255) NOT NULL,
	part_definition_id int8 NOT NULL,
	CONSTRAINT question_definitions_pkey PRIMARY KEY (id),
	CONSTRAINT fk1i5ei4kxkd3inbt28g7nkbk2y FOREIGN KEY (part_definition_id) REFERENCES public.part_definitions(id)
);


-- public.users definition

-- Drop table

-- DROP TABLE public.users;

CREATE TABLE public.users (
	id bigserial NOT NULL,
	email varchar(255) NOT NULL,
	first_name varchar(255) NOT NULL,
	job_title varchar(255) NOT NULL,
	last_name varchar(255) NOT NULL,
	"password" varchar(255) NOT NULL,
	years_of_experience_technology varchar(255) NOT NULL,
	company_id int8 NOT NULL,
	CONSTRAINT uk_6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email),
	CONSTRAINT users_pkey PRIMARY KEY (id),
	CONSTRAINT fkin8gn4o1hpiwe6qe4ey7ykwq7 FOREIGN KEY (company_id) REFERENCES public.companies(id)
);


-- public.submissions definition

-- Drop table

-- DROP TABLE public.submissions;

CREATE TABLE public.submissions (
	id bigserial NOT NULL,
	submission_date timestamp(6) NOT NULL,
	form_definition_id int8 NOT NULL,
	user_id int8 NOT NULL,
	CONSTRAINT submissions_pkey PRIMARY KEY (id),
	CONSTRAINT fk43fsjocacl2w4e92sm5o0agh9 FOREIGN KEY (form_definition_id) REFERENCES public.form_definitions(id),
	CONSTRAINT fk760bgu69957phd7hax608jdms FOREIGN KEY (user_id) REFERENCES public.users(id)
);


-- public.submission_parts definition

-- Drop table

-- DROP TABLE public.submission_parts;

CREATE TABLE public.submission_parts (
	id bigserial NOT NULL,
	part_definition_id int8 NOT NULL,
	submission_id int8 NOT NULL,
	CONSTRAINT submission_parts_pkey PRIMARY KEY (id),
	CONSTRAINT fkbcnuddyfkw1ga98vqagw43kj6 FOREIGN KEY (part_definition_id) REFERENCES public.part_definitions(id),
	CONSTRAINT fkigwnd5797hcb94xaqasbmxbep FOREIGN KEY (submission_id) REFERENCES public.submissions(id)
);


-- public.submission_questions definition

-- Drop table

-- DROP TABLE public.submission_questions;

CREATE TABLE public.submission_questions (
	id bigserial NOT NULL,
	actual_level int4 NOT NULL,
	target_level int4 NOT NULL,
	question_definition_id int8 NOT NULL,
	submission_part_id int8 NOT NULL,
	CONSTRAINT submission_questions_pkey PRIMARY KEY (id),
	CONSTRAINT fkbsgseq3rxlx9fy04wuaj7j0n0 FOREIGN KEY (question_definition_id) REFERENCES public.question_definitions(id),
	CONSTRAINT fkhquq37i4ej0ltbwumpuxjed4h FOREIGN KEY (submission_part_id) REFERENCES public.submission_parts(id)
);


-- public.submission_part_metrics definition

-- Drop table

-- DROP TABLE public.submission_part_metrics;

CREATE TABLE public.submission_part_metrics (
	id bigserial NOT NULL,
	average_actual_score int4 NULL,
	average_desired_score int4 NULL,
	majority_cut_off_level int4 NULL,
	qualified_majority_criterion numeric(38, 2) NULL,
	threshold_based_scoring numeric(38, 2) NULL,
	submission_part_id int8 NOT NULL,
	CONSTRAINT submission_part_metrics_pkey PRIMARY KEY (id),
	CONSTRAINT uk_6opjhsimehwuomoldxho3jsna UNIQUE (submission_part_id),
	CONSTRAINT fkq2njk2wh9jfrx55se5jgtg9hg FOREIGN KEY (submission_part_id) REFERENCES public.submission_parts(id)
);

INSERT INTO public.companies (id,country,"name",number_of_employees,organization_sector,organization_type) VALUES
	 (1,'USA','Test Company','50-100','Technology','Private');
INSERT INTO public.form_definitions (id,form_name,form_version) VALUES
	 (1,'Autoevaluacion','1.0');
INSERT INTO public.part_definitions (id,part_name,part_number,form_definition_id) VALUES
	 (1,'Estrategia de TI',1,1);
INSERT INTO public.question_definitions (id,question_number,question_text,part_definition_id) VALUES
	 (1,1,'¿Cómo describirías la planificación de tecnología de información en tu organización?',1),
	 (2,2,'¿Cómo se priorizan las inversiones en TI en tu organización?',1),
	 (3,3,'¿Qué papel juega TI en la estrategia organizacional?',1),
	 (4,4,'¿Cómo se gestionan los recursos de TI?',1),
	 (5,5,'¿Cuál es la visión a largo plazo de TI en tu organización?',1);
INSERT INTO public.users (id,email,first_name,job_title,last_name,"password",years_of_experience_technology,company_id) VALUES
	 (1,'alice.smith@example.com','Alice','Software Engineer','Smith','$2a$10$QNMmwBz05T2PXQ6Uw7Q1SOuQ6bWFcgI9owKKL.q00HiNkckLWdriO','3',1);

INSERT INTO public.answer_option_definitions (id,description,option_level,question_definition_id) VALUES
	 (1,'Ad hoc, sin estructura definida.',1,1),
	 (2,'Algunas prioridades claras, pero sin un plan estructurado.',2,1),
	 (3,'Existencia de un plan documentado y presupuestos definidos.',3,1),
	 (4,'Completa alineación con la estrategia empresarial.',4,1),
	 (5,'Estrategia empresarial liderada por lo digital.',5,1),
	 (6,'Basadas en demandas inmediatas, sin estrategia a largo plazo.',1,2),
	 (7,'Con alguna consideración a la relevancia estratégica.',2,2),
	 (8,'Se priorizan según un plan de TI que refleja necesidades a medio plazo.',3,2),
	 (9,'En alineación con objetivos estratégicos claros y a largo plazo.',4,2),
	 (10,'Liderando la dirección estratégica de la empresa, con TI como un motor clave.',5,2);
INSERT INTO public.answer_option_definitions (id,description,option_level,question_definition_id) VALUES
	 (11,'Papel limitado y reactivo.',1,3),
	 (12,'Reconocido, pero no completamente integrado.',2,3),
	 (13,'Considerado en la planificación estratégica.',3,3),
	 (14,'Clave en la definición de estrategias empresariales.',4,3),
	 (15,'Impulsor principal de la estrategia empresarial.',5,3),
	 (16,'Asignación según demanda, sin planificación.',1,4),
	 (17,'Con esfuerzos para ajustarse a las necesidades más urgentes.',2,4),
	 (18,'Con recursos asignados según un plan de desarrollo de TI.',3,4),
	 (19,'Optimizados para alinear con la estrategia empresarial.',4,4),
	 (20,'Máxima eficiencia y efectividad, liderando la transformación digital.',5,4);
INSERT INTO public.answer_option_definitions (id,description,option_level,question_definition_id) VALUES
	 (21,'No se contempla una visión a largo plazo.',1,5),
	 (22,'Visión a corto plazo, centrada en resolver problemas operativos.',2,5),
	 (23,'Visión de mediano plazo, con objetivos claros.',3,5),
	 (24,'Visión estratégica integrada con los planes empresariales.',4,5),
	 (25,'Visionaria y transformadora, con TI como eje central del desarrollo empresarial.',5,5);