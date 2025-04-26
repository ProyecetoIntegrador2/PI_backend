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
