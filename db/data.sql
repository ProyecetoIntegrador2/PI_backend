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


-- public.answer_option_definitions definition

-- Drop table

-- DROP TABLE public.answer_option_definitions;

CREATE TABLE public.answer_option_definitions (
	id bigserial NOT NULL,
	description varchar(255) NOT NULL,
	option_level int4 NOT NULL,
	question_definition_id int8 NOT NULL,
	CONSTRAINT answer_option_definitions_pkey PRIMARY KEY (id),
	CONSTRAINT fkevb5akm2o8b3xd8epl5xike2p FOREIGN KEY (question_definition_id) REFERENCES public.question_definitions(id)
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


-- public.submission_metrics definition

-- Drop table

-- DROP TABLE public.submission_metrics;

CREATE TABLE public.submission_metrics (
	id bigserial NOT NULL,
	average_actual_score int4 NULL,
	average_desired_score int4 NULL,
	majority_cut_off_level int4 NULL,
	threshold_based_scoring int4 NULL,
	submission_id int8 NOT NULL,
	CONSTRAINT submission_metrics_pkey PRIMARY KEY (id),
	CONSTRAINT uk_q5k38g9prtkcj3rl60sab5tbr UNIQUE (submission_id),
	CONSTRAINT fkth0lwvk1l4pooay0scy65fkt7 FOREIGN KEY (submission_id) REFERENCES public.submissions(id)
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


-- public.submission_part_metrics definition

-- Drop table

-- DROP TABLE public.submission_part_metrics;

CREATE TABLE public.submission_part_metrics (
	id bigserial NOT NULL,
	average_actual_score int4 NULL,
	average_desired_score int4 NULL,
	majority_cut_off_level int4 NULL,
	threshold_based_scoring int4 NULL,
	submission_part_id int8 NOT NULL,
	CONSTRAINT submission_part_metrics_pkey PRIMARY KEY (id),
	CONSTRAINT uk_6opjhsimehwuomoldxho3jsna UNIQUE (submission_part_id),
	CONSTRAINT fkq2njk2wh9jfrx55se5jgtg9hg FOREIGN KEY (submission_part_id) REFERENCES public.submission_parts(id)
);


-- public.submission_questions definition

-- Drop table

-- DROP TABLE public.submission_questions;

CREATE TABLE public.submission_questions (
	id bigserial NOT NULL,
	actual_option_id int8 NOT NULL,
	question_definition_id int8 NOT NULL,
	submission_part_id int8 NOT NULL,
	target_option_id int8 NOT NULL,
	CONSTRAINT submission_questions_pkey PRIMARY KEY (id),
	CONSTRAINT fkbgxr14f9ebiwuwd2bnf9i028d FOREIGN KEY (target_option_id) REFERENCES public.answer_option_definitions(id),
	CONSTRAINT fkbsgseq3rxlx9fy04wuaj7j0n0 FOREIGN KEY (question_definition_id) REFERENCES public.question_definitions(id),
	CONSTRAINT fkhquq37i4ej0ltbwumpuxjed4h FOREIGN KEY (submission_part_id) REFERENCES public.submission_parts(id),
	CONSTRAINT fkiie0t42ox9h27pei5q8xt825g FOREIGN KEY (actual_option_id) REFERENCES public.answer_option_definitions(id)
);

INSERT INTO public.form_definitions (form_name,form_version) VALUES
	 ('Autoevaluacion','1.0');
INSERT INTO public.part_definitions (part_name,part_number,form_definition_id) VALUES
	 ('Estrategia de TI',1,1),
	 ('Inteligencia de Datos',2,1),
	 ('Capacidad de TI ',3,1),
	 ('Procesos',4,1),
	 ('Experiencia del usuario ',5,1),
	 ('Riesgo y Cumplimiento',6,1),
	 ('Cultura Digital',7,1);
INSERT INTO public.question_definitions (question_number,question_text,part_definition_id) VALUES
	 (1,'¿Cómo describirías la planificación de tecnología de información en tu organización?',1),
	 (2,'¿Cómo se priorizan las inversiones en TI en tu organización?',1),
	 (3,'¿Qué papel juega TI en la estrategia organizacional?',1),
	 (4,'¿Cómo se gestionan los recursos de TI?',1),
	 (5,'¿Cuál es la visión a largo plazo de TI en tu organización?',1),
	 (6,'¿Qué tipo de informes utiliza tu organización para la toma de decisiones?',2),
	 (7,'¿Cómo se recolectan y analizan los datos en tu organización?',2),
	 (8,'¿Cuál es el nivel de accesibilidad a los datos en tu organización?',2),
	 (9,'¿Qué tan avanzadas son las capacidades de análisis de datos de tu organización?',2),
	 (10,'¿Cómo se utilizan los datos para apoyar la estrategia de la organización?',2);
INSERT INTO public.question_definitions (question_number,question_text,part_definition_id) VALUES
	 (11,'¿Cómo describirías las habilidades digitales generales en tu organización?',3),
	 (12,'¿Cómo se gestiona el talento digital en tu organización?',3),
	 (13,'¿Cuál es la capacidad de adaptación tecnológica de tu organización?',3),
	 (14,'¿Cómo se priorizan y se implementan las inversiones en tecnología en tu organización?',3),
	 (15,'¿Cómo se evalúa el impacto de las tecnologías implementadas en tu organización?',3),
	 (16,'¿Cómo se gestionan los procesos operativos en tu organización?',4),
	 (17,'¿Qué nivel de integración tecnológica existe en los procesos de negocio?',4),
	 (18,'¿Cómo se documentan y se mantienen los procesos en tu organización?',4),
	 (19,'¿Cómo responde tu organización a cambios en los procesos requeridos por nuevas necesidades de negocio?',4),
	 (20,'¿Cómo se mide la eficiencia de los procesos en tu organización?',4);
INSERT INTO public.question_definitions (question_number,question_text,part_definition_id) VALUES
	 (21,'¿Cómo se evalúa la experiencia del usuario en los servicios digitales ofrecidos?',5),
	 (22,'¿Qué enfoque se hacia el diseño de la experiencia del usuario?',5),
	 (23,'¿Cómo se integra la retroalimentación del usuario en el desarrollo de productos y servicios?',5),
	 (24,'¿Cómo se gestionan las expectativas de los usuarios en relación con la tecnología y servicios ofrecidos?',5),
	 (25,'¿Qué importancia tiene la accesibilidad en los servicios digitales de tu organización?',5),
	 (26,'¿Cómo se gestiona el riesgo digital en tu organización?',6),
	 (27,'¿Cómo se asegura el cumplimiento de normativas en tu organización?',6),
	 (28,'¿Cuál es el nivel de conciencia sobre seguridad digital en tu organización?',6),
	 (29,'¿Cómo se gestionan los datos y la privacidad de los usuarios?',6),
	 (30,'¿Cómo se identifican y gestionan los riesgos emergentes en tecnología?',6);
INSERT INTO public.question_definitions (question_number,question_text,part_definition_id) VALUES
	 (31,'¿Cómo describirías la cultura digital en tu organización?',7),
	 (32,'¿Cómo se promueve la innovación digital en tu organización?',7),
	 (33,'¿Cómo se entrena a los empleados en herramientas y prácticas digitales?',7),
	 (34,'¿Cómo se maneja el cambio hacia la digitalización en tu organización?',7),
	 (35,'¿Cuál es la actitud general hacia el uso y adopción de nuevas tecnologías?',7);
INSERT INTO public.answer_option_definitions (description,option_level,question_definition_id) VALUES
	 ('Ad hoc, sin estructura definida.',1,1),
	 ('Algunas prioridades claras, pero sin un plan estructurado.',2,1),
	 ('Existencia de un plan documentado y presupuestos definidos.',3,1),
	 ('Completa alineación con la estrategia empresarial.',4,1),
	 ('Estrategia empresarial liderada por lo digital.',5,1),
	 ('Basadas en demandas inmediatas, sin estrategia a largo plazo.',1,2),
	 ('Con alguna consideración a la relevancia estratégica.',2,2),
	 ('Se priorizan según un plan de TI que refleja necesidades a medio plazo.',3,2),
	 ('En alineación con objetivos estratégicos claros y a largo plazo.',4,2),
	 ('Liderando la dirección estratégica de la empresa, con TI como un motor clave.',5,2);
INSERT INTO public.answer_option_definitions (description,option_level,question_definition_id) VALUES
	 ('Papel limitado y reactivo.',1,3),
	 ('Reconocido, pero no completamente integrado.',2,3),
	 ('Considerado en la planificación estratégica.',3,3),
	 ('Clave en la definición de estrategias empresariales.',4,3),
	 ('Impulsor principal de la estrategia empresarial.',5,3),
	 ('Asignación según demanda, sin planificación.',1,4),
	 ('Con esfuerzos para ajustarse a las necesidades más urgentes.',2,4),
	 ('Con recursos asignados según un plan de desarrollo de TI.',3,4),
	 ('Optimizados para alinear con la estrategia empresarial.',4,4),
	 ('Máxima eficiencia y efectividad, liderando la transformación digital.',5,4);
INSERT INTO public.answer_option_definitions (description,option_level,question_definition_id) VALUES
	 ('No se contempla una visión a largo plazo.',1,5),
	 ('Visión a corto plazo, centrada en resolver problemas operativos.',2,5),
	 ('Visión de mediano plazo, con objetivos claros.',3,5),
	 ('Visión estratégica integrada con los planes empresariales.',4,5),
	 ('Visionaria y transformadora, con TI como eje central del desarrollo empresarial.',5,5),
	 ('Informes estándar o predeterminados.',1,6),
	 ('Informes personalizados según necesidades específicas.',2,6),
	 ('Tableros de control con indicadores de gestión (BI).',3,6),
	 ('Análisis predictivos y prescriptivos.',4,6),
	 ('Decisiones basadas en inteligencia artificial.',5,6);
INSERT INTO public.answer_option_definitions (description,option_level,question_definition_id) VALUES
	 ('Manualmente, con herramientas básicas como Excel.',1,7),
	 ('Con herramientas especializadas, pero sin integración de datos.',2,7),
	 ('Uso de herramientas BI integradas para la visualización de datos.',3,7),
	 ('Análisis avanzado con uso de big data y analytics.',4,7),
	 ('Automatización completa y aprendizaje automático.',5,7),
	 ('Acceso limitado y controlado manualmente.',1,8),
	 ('Acceso parcial con algunos roles definidos.',2,8),
	 ('Amplio acceso para usuarios autorizados con control de seguridad.',3,8),
	 ('Datos accesibles y seguros en tiempo real para decisiones rápidas.',4,8),
	 ('Plena democratización de los datos con gobernanza eficiente.',5,8);
INSERT INTO public.answer_option_definitions (description,option_level,question_definition_id) VALUES
	 ('Análisis básicos y descriptivos.',1,9),
	 ('Análisis algo avanzados con cierta capacidad predictiva.',2,9),
	 ('Capacidad de análisis predictivo y de tendencias.',3,9),
	 ('Análisis prescriptivo integrado en procesos operativos.',4,9),
	 ('Análisis predictivo y prescriptivo generalizado, basado en IA.',5,9),
	 ('Los datos no son considerados en la estrategia.',1,10),
	 ('Se utilizan datos para informar decisiones tácticas.',2,10),
	 ('Los datos están al centro de muchas decisiones estratégicas.',3,10),
	 ('Los datos guían casi todas las decisiones estratégicas y operativas.',4,10),
	 ('La estrategia organizacional es impulsada por análisis de datos avanzados.',5,10);
INSERT INTO public.answer_option_definitions (description,option_level,question_definition_id) VALUES
	 ('Habilidades esenciales mínimas.',1,11),
	 ('Reactivas a las necesidades inmediatas.',2,11),
	 ('En desarrollo activo y mejora continua.',3,11),
	 ('Competencias digitales bien establecidas.',4,11),
	 ('Especialización y liderazgo en habilidades digitales.',5,11),
	 ('Sin estrategia definida para el desarrollo de talento digital.',1,12),
	 ('Desarrollo de talento basado en necesidades puntuales.',2,12),
	 ('Planes de desarrollo de talento con objetivos claros.',3,12),
	 ('Gestión proactiva y estratégica del talento digital.',4,12),
	 ('Cultura de excelencia y liderazgo en capacidades digitales.',5,12);
INSERT INTO public.answer_option_definitions (description,option_level,question_definition_id) VALUES
	 ('Adaptación mínima y lenta a nuevas tecnologías.',1,13),
	 ('Adaptación reactiva a tecnologías probadas.',2,13),
	 ('Exploración activa de nuevas tecnologías para mejorar procesos.',3,13),
	 ('Adopción rápida y efectiva de tecnologías avanzadas.',4,13),
	 ('Innovación continua y liderazgo en la adopción tecnológica.',5,13),
	 ('Inversiones mínimas y esporádicas.',1,14),
	 ('Inversiones según urgencias o demandas inmediatas.',2,14),
	 ('Inversiones planificadas según un roadmap tecnológico.',3,14),
	 ('Inversiones estratégicas con visión de futuro.',4,14),
	 ('Inversiones lideradas por necesidades de transformación digital.',5,14);
INSERT INTO public.answer_option_definitions (description,option_level,question_definition_id) VALUES
	 ('Evaluación esporádica o inexistente.',1,15),
	 ('Evaluaciones reactivas basadas en problemas surgidos.',2,15),
	 ('Evaluaciones sistemáticas del rendimiento y la eficacia.',3,15),
	 ('Evaluaciones integradas en la gestión del cambio.',4,15),
	 ('Evaluaciones proactivas con ajustes continuos basados en resultados.',5,15),
	 ('Mayormente manuales y dependientes de intervención humana.',1,16),
	 ('Parcialmente digitalizados, pero aún dependientes de sistemas aislados.',2,16),
	 ('Básicamente automatizados con integración de sistemas como ERP/CRM.',3,16),
	 ('Automatización avanzada con optimización continua.',4,16),
	 ('Automatización completa con robótica y procesos inteligentes.',5,16);
INSERT INTO public.answer_option_definitions (description,option_level,question_definition_id) VALUES
	 ('Integración mínima o nula.',1,17),
	 ('Algunos procesos clave están digitalizados.',2,17),
	 ('Integración de sistemas críticos con flujos de trabajo digitalizados.',3,17),
	 ('Alta integración con flujos de trabajo optimizados digitalmente.',4,17),
	 ('Integración total y transversal con tecnologías de vanguardia.',5,17),
	 ('Documentación ad hoc, inconsistente.',1,18),
	 ('Documentación básica, centrada en procesos clave.',2,18),
	 ('Procesos bien documentados y revisados regularmente.',3,18),
	 ('Documentación y mantenimiento proactivos con mejoras continuas.',4,18),
	 ('Gestionados en tiempo real, con ajustes basados en análisis predictivos.',5,18);
INSERT INTO public.answer_option_definitions (description,option_level,question_definition_id) VALUES
	 ('Respuestas lentas y a menudo ineficaces.',1,19),
	 ('Capacidad de respuesta limitada, enfocada en áreas críticas.',2,19),
	 ('Respuestas planificadas y gestionadas a nivel de proyecto.',3,19),
	 ('Adaptación rápida y efectiva a través de procesos ágiles.',4,19),
	 ('Innovación y reconfiguración continua de procesos.',5,19),
	 ('Mediciones esporádicas o basadas en percepciones.',1,20),
	 ('KPIs básicos aplicados a procesos seleccionados.',2,20),
	 ('Amplia monitorización con KPIs integrados en operaciones.',3,20),
	 ('Análisis continuo de rendimiento con optimización basada en datos.',4,20),
	 ('Monitoreo en tiempo real con ajustes automatizados y predicción de necesidades.',5,20);
INSERT INTO public.answer_option_definitions (description,option_level,question_definition_id) VALUES
	 ('No se realiza evaluación sistemática.',1,21),
	 ('Evaluaciones ocasionales basadas en feedback directo.',2,21),
	 ('Evaluaciones regulares con métricas de satisfacción del usuario.',3,21),
	 ('Análisis continuo con ajustes basados en la experiencia del usuario.',4,21),
	 ('Optimización constante basada en datos avanzados y AI para personalización.',5,21),
	 ('Diseño reactivo y basado en soluciones existentes.',1,22),
	 ('Algunos proyectos diseñados con enfoque en el usuario.',2,22),
	 ('Diseño centrado en el usuario para todos los nuevos desarrollos.',3,22),
	 ('Diseño proactivo basado en estudios de usuario y pruebas de usabilidad.',4,22),
	 ('Innovación continua en experiencia del usuario con enfoque en personalización y accesibilidad.',5,22);
INSERT INTO public.answer_option_definitions (description,option_level,question_definition_id) VALUES
	 ('Raramente se considera la retroalimentación del usuario.',1,23),
	 ('Consideración ocasional, especialmente en fallos o quejas.',2,23),
	 ('Sistemática integración de feedback en ciclos de mejora.',3,23),
	 ('Retroalimentación integral en el proceso de desarrollo continuo.',4,23),
	 ('Ciclos de retroalimentación dinámicos y adaptativos que impulsan el desarrollo de productos.',5,23),
	 ('Gestión mínima o desconexión con las expectativas del usuario.',1,24),
	 ('Esfuerzos para alinear expectativas en puntos de contacto claves.',2,24),
	 ('Gestión activa y comunicación clara de lo que los usuarios pueden esperar.',3,24),
	 ('Expectativas gestionadas proactivamente con promesas cumplidas y superadas.',4,24),
	 ('Anticipación y superación de expectativas mediante innovaciones y mejoras continuas.',5,24);
INSERT INTO public.answer_option_definitions (description,option_level,question_definition_id) VALUES
	 ('No se considera la accesibilidad como un factor importante.',1,25),
	 ('Algunos esfuerzos para cumplir con requisitos mínimos de accesibilidad.',2,25),
	 ('Compromiso claro con la accesibilidad en todos los servicios digitales.',3,25),
	 ('Accesibilidad integrada como un estándar en el desarrollo de productos.',4,25),
	 ('Liderazgo en accesibilidad, estableciendo nuevas normas en la industria.',5,25),
	 ('Gestión de riesgos ad hoc, sin un enfoque sistemático.',1,26),
	 ('Algunas políticas de riesgo establecidas, pero aplicación inconsistente.',2,26),
	 ('Políticas de riesgo robustas con cumplimiento regularmente revisado.',3,26),
	 ('Gestión integrada de riesgos con tecnologías de monitoreo en tiempo real.',4,26),
	 ('Predicción y mitigación proactiva de riesgos basada en inteligencia artificial y datos.',5,26);
INSERT INTO public.answer_option_definitions (description,option_level,question_definition_id) VALUES
	 ('Cumplimiento esporádico o cuando es estrictamente necesario.',1,27),
	 ('Cumplimiento de las principales normativas, con algunos vacíos.',2,27),
	 ('Programa completo de cumplimiento normativo.',3,27),
	 ('Cumplimiento proactivo con auditorías y ajustes regulares.',4,27),
	 ('Cumplimiento avanzado con adaptaciones automáticas a cambios legislativos.',5,27),
	 ('Conciencia mínima, centrada en solucionar brechas después de que ocurren.',1,28),
	 ('Conciencia básica con formación ocasional para empleados.',2,28),
	 ('Programas regulares de capacitación en seguridad y políticas claras.',3,28),
	 ('Cultura de seguridad con evaluaciones y mejoras continuas.',4,28),
	 ('Seguridad integrada en todos los niveles organizacionales, con enfoque en prevención.',5,28);
INSERT INTO public.answer_option_definitions (description,option_level,question_definition_id) VALUES
	 ('Gestión mínima, cumpliendo solo con los requisitos básicos legales.',1,29),
	 ('Políticas de privacidad establecidas pero no siempre aplicadas rigurosamente.',2,29),
	 ('Cumplimiento firme con políticas de privacidad y seguridad de datos.',3,29),
	 ('Gestión avanzada con auditorías regulares y transparencia hacia los usuarios.',4,29),
	 ('Liderazgo en privacidad y seguridad de datos, con innovación continua en protección de datos.',5,29),
	 ('Identificación y gestión reactiva, generalmente después de que los problemas se manifiestan.',1,30),
	 ('Alguna previsión de riesgos con medidas básicas de mitigación.',2,30),
	 ('Identificación proactiva y planes de mitigación establecidos.',3,30),
	 ('Monitoreo continuo con ajustes rápidos a la estrategia de riesgos.',4,30),
	 ('Gestión anticipativa de riesgos, utilizando análisis predictivo y adaptación continua.',5,30);
INSERT INTO public.answer_option_definitions (description,option_level,question_definition_id) VALUES
	 ('Incipiente y sin un enfoque claro hacia lo digital.',1,31),
	 ('Reconocimiento de la importancia digital, con iniciativas aisladas.',2,31),
	 ('Compromiso con la transformación digital, con políticas de apoyo.',3,31),
	 ('Cultura digital bien establecida con adopción generalizada.',4,31),
	 ('Liderazgo y excelencia en prácticas digitales, con innovación constante.',5,31),
	 ('Esporádicamente y sin estructura formal.',1,32),
	 ('A través de iniciativas puntuales, sin un enfoque integrado.',2,32),
	 ('Con programas definidos para fomentar la innovación.',3,32),
	 ('Mediante una estrategia de innovación integrada en todos los departamentos.',4,32),
	 ('Como un elemento central de la cultura organizacional, con recursos y soporte continuo.',5,32);
INSERT INTO public.answer_option_definitions (description,option_level,question_definition_id) VALUES
	 ('Formación mínima o inexistente.',1,33),
	 ('Capacitaciones ocasionales o reactivas a necesidades inmediatas.',2,33),
	 ('Programas estructurados de formación digital.',3,33),
	 ('Capacitación continua y adaptativa a nuevas tecnologías.',4,33),
	 ('Desarrollo proactivo del talento digital con enfoque en competencias futuras.',5,33),
	 ('Resistencia al cambio y adaptación mínima.',1,34),
	 ('Adaptación gradual con aceptación variada.',2,34),
	 ('Gestión activa del cambio con recursos dedicados.',3,34),
	 ('Cambio bien gestionado con participación y apoyo amplios.',4,34),
	 ('Cambio como un proceso continuo y parte de la cultura organizacional.',5,34);
INSERT INTO public.answer_option_definitions (description,option_level,question_definition_id) VALUES
	 ('Cautelosa y reacia.',1,35),
	 ('Abierta pero no prioritaria.',2,35),
	 ('Proactiva y estratégica.',3,35),
	 ('Entusiasta y orientada a la eficiencia.',4,35),
	 ('Visionaria y pionera, buscando constantemente liderar a través de la tecnología.',5,35);
INSERT INTO public.companies (country,"name",number_of_employees,organization_sector,organization_type) VALUES
	 ('USA','Test Company','50-100','Technology','Private');
INSERT INTO public.users (email,first_name,job_title,last_name,"password",years_of_experience_technology,company_id) VALUES
	 ('alice.smith@example.com','Alice','Software Engineer','Smith','$2a$10$QNMmwBz05T2PXQ6Uw7Q1SOuQ6bWFcgI9owKKL.q00HiNkckLWdriO','3',1);
