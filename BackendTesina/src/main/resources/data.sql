INSERT INTO USERS (email,name,lastname, password, role) VALUES ('nahuelvegavega@gmail.com', 'Nahuel','Vega','$2a$10$WhRuuQWNRMKNSzjALksRieneowHvpXjj5NXMxdZHr7Zl.cpFEp4.q', 'SUPERADMIN'),
                                                               ('usuario@gmail.com', 'User','Test','$2a$10$DyDlgM58UpT4W2B2yj5Pqe0cW1tGCUR3K1MellY1dyVvti4fGSXTy', 'USER'),
                                                               ('admin@gmail.com', 'Admin','Test','$2a$10$DyDlgM58UpT4W2B2yj5Pqe0cW1tGCUR3K1MellY1dyVvti4fGSXTy', 'ADMIN'),
                                                               ('empleado@gmail.com', 'Empleado','Test','$2a$10$DyDlgM58UpT4W2B2yj5Pqe0cW1tGCUR3K1MellY1dyVvti4fGSXTy', 'EMPLOYEE'),
                                                                ('maria.lopez@gmail.com', 'María', 'López', '$2a$10$DyDlgM58UpT4W2B2yj5Pqe0cW1tGCUR3K1MellY1dyVvti4fGSXTy', 'USER'),
                                                                 ('carlos.garcia@gmail.com', 'Carlos', 'García', '$2a$10$DyDlgM58UpT4W2B2yj5Pqe0cW1tGCUR3K1MellY1dyVvti4fGSXTy', 'USER'),
                                                                 ('ana.martinez@gmail.com', 'Ana', 'Martínez', '$2a$10$DyDlgM58UpT4W2B2yj5Pqe0cW1tGCUR3K1MellY1dyVvti4fGSXTy', 'USER'),
                                                                 ('juan.gomez@gmail.com', 'Juan', 'Gómez', '$2a$10$DyDlgM58UpT4W2B2yj5Pqe0cW1tGCUR3K1MellY1dyVvti4fGSXTy', 'USER'),
                                                                 ('sofia.fernandez@gmail.com', 'Sofía', 'Fernández', '$2a$10$DyDlgM58UpT4W2B2yj5Pqe0cW1tGCUR3K1MellY1dyVvti4fGSXTy', 'USER'),
                                                                 ('lucas.perez@gmail.com', 'Lucas', 'Pérez', '$2a$10$DyDlgM58UpT4W2B2yj5Pqe0cW1tGCUR3K1MellY1dyVvti4fGSXTy', 'USER'),
                                                                 ('valeria.suarez@gmail.com', 'Valeria', 'Suárez', '$2a$10$DyDlgM58UpT4W2B2yj5Pqe0cW1tGCUR3K1MellY1dyVvti4fGSXTy', 'USER'),
                                                                 ('diego.mendez@gmail.com', 'Diego', 'Méndez', '$2a$10$DyDlgM58UpT4W2B2yj5Pqe0cW1tGCUR3K1MellY1dyVvti4fGSXTy', 'USER'),
                                                                ('andrea.nunez@gmail.com', 'Andrea', 'Núñez', '$2a$10$DyDlgM58UpT4W2B2yj5Pqe0cW1tGCUR3K1MellY1dyVvti4fGSXTy', 'USER'),
                                                                ('pedro.alvarado@gmail.com', 'Pedro', 'Alvarado', '$2a$10$DyDlgM58UpT4W2B2yj5Pqe0cW1tGCUR3K1MellY1dyVvti4fGSXTy', 'USER');

INSERT INTO PROVIDERS (
    name, adress, category, city, country,
    CUIT, email, phone, state, register_date, remarks
) VALUES
      ('Autopartes Córdoba', 'Av. Colón 1500', 'MOTOR', 'CÓRDOBA', 'ARGENTINA',
       20334455667, 'ventas@autopartescordoba.com', 543514567890, true, '2024-03-01', 'Entrega rápida y precios competitivos'),

      ('Lubricantes del Centro', 'Calle Mitre 234', 'LUBRICACION', 'ROSARIO', 'ARGENTINA',
       30445566778, 'contacto@lubricantescentro.com', 543416543210, true, '2024-04-10', 'Especialistas en aceites y lubricantes'),

      ('Refrigeración Total', 'Ruta 20 km 12', 'REFRIGERACION', 'MENDOZA', 'ARGENTINA',
       27556677889, 'info@refrigeraciontotal.com', 542619876543, false, '2024-05-15', 'Amplia variedad de productos de refrigeración'),

      ('Motores y Más', 'Av. Rivadavia 890', 'MOTOR', 'BUENOS AIRES', 'ARGENTINA',
       20667788990, 'soporte@motoresymas.com', 541145678901, true, '2024-06-20', 'Proveedor confiable con soporte técnico'),

      ('LubriExpress', 'Calle San Martín 456', 'LUBRICACION', 'SALTA', 'ARGENTINA',
       30778899001, 'ventas@lubriexpress.com', 543871234567, true, '2024-07-05', 'Entrega en 24 horas en todo el país'),

      ('Clima Auto', 'Av. Belgrano 1234', 'REFRIGERACION', 'TUCUMÁN', 'ARGENTINA',
       27889900112, 'contacto@climaauto.com', 543819876543, false, '2023-12-01', 'Actualmente en reestructuración'),

      ('Partes del Sur', 'Ruta 3 km 45', 'MOTOR', 'NEUQUÉN', 'ARGENTINA',
       20990011223, 'info@partesdelsur.com', 542991234567, true, '2024-08-10', 'Especialistas en repuestos para motores diésel'),

      ('Aceites Premium', 'Calle Independencia 789', 'LUBRICACION', 'SAN JUAN', 'ARGENTINA',
       30001122334, 'ventas@aceitespremium.com', 542644567890, true, '2024-09-25', 'Productos de alta calidad y certificación internacional'),

      ('Frío Motor', 'Av. Libertador 567', 'REFRIGERACION', 'SAN LUIS', 'ARGENTINA',
       27112233445, 'soporte@friomotor.com', 542669876543, false, '2024-10-12', 'Proveedor con experiencia en sistemas de enfriamiento'),

      ('Repuestos del Norte', 'Calle Güemes 321', 'MOTOR', 'RESISTENCIA', 'ARGENTINA',
       20223344556, 'contacto@repuestosnorte.com', 543621234567, true, '2024-11-18', 'Amplio stock de repuestos para vehículos livianos');

INSERT INTO SPARES
(name, active, stars, price, discaunt, stock, brand, category, description, image1, image2, image3, image4, image5, provider_id)
VALUES
    ('Aceite 10W40', true, 5.00, 12000.00, 10, 50, 'Shell', 'LUBRICACION', 'Aceite sintético para motores modernos', 'https://http2.mlstatic.com/D_NQ_NP_760369-MLA76111407169_042024-O.webp', NULL, NULL, NULL, NULL, 1),
    ('Filtro de Aire', true, 4.00, 4500.00, 5, 30, 'Bosch', 'MOTOR', 'Filtro de aire de alto rendimiento', 'https://http2.mlstatic.com/D_NQ_NP_707748-MLA31604241224_072019-O.webp', NULL, NULL, NULL, NULL, 2),
    ('Radiador Compacto', true, 3.00, 25000.00, 15, 10, 'Valeo', 'REFRIGERACION', 'Radiador de aluminio compacto', 'https://http2.mlstatic.com/D_NQ_NP_609279-MLM52131448379_102022-O.webp', NULL, NULL, NULL, NULL, 3),
    ('Bujías Iridium', true, 3.33, 6000.00, 0, 80, 'NGK', 'MOTOR', 'Juego de 4 bujías de iridio', 'https://http2.mlstatic.com/D_NQ_NP_991346-MLA72649285452_112023-O.webp', NULL, NULL, NULL, NULL, 4),
    ('Batería 12V 65Ah', true, 2.0, 35000.00, 5, 20, 'Willard', 'MOTOR', 'Batería de larga duración para autos medianos', 'https://http2.mlstatic.com/D_NQ_NP_690789-MLA78040694005_072024-O.webp', NULL, NULL, NULL, NULL, 5),
    ('Filtro de Aceite', true, 1.0, 3000.00, 0, 100, 'Mann', 'LUBRICACION', 'Filtro de aceite compatible con motores diésel y nafteros', 'https://cdn.motordoctor.de/thumb?id=963560&m=1&n=0&lng=es&ccf=94077854', NULL, NULL, NULL, NULL, 1),
    ('Ventilador Electrico', true, 4.0, 18000.00, 10, 15, 'Delphi', 'REFRIGERACION', 'Ventilador para sistema de enfriamiento del motor', 'https://m.media-amazon.com/images/I/71++HklIHTL.jpg', NULL, NULL, NULL, NULL, 3),
    ('Correa Dentada', true, 5, 7500.00, 0, 40, 'Gates', 'MOTOR', 'Correa dentada de 120 dientes para motor 1.6', 'https://http2.mlstatic.com/D_NQ_NP_994898-MLA41621919030_052020-O.webp', NULL, NULL, NULL, NULL, 2),
    ('Termostato 90°C', true, 4.5, 4200.00, 0, 25, 'Mahle', 'REFRIGERACION', 'Termostato para regulación de temperatura del motor', 'https://http2.mlstatic.com/D_869555-MLA76562425378_062024-C.jpg', NULL, NULL, NULL, NULL, 3),
    ('Lubricante Transmisión', true, 1.1, 9800.00, 8, 60, 'Castrol', 'LUBRICACION', 'Aceite para caja de cambios de alta viscosidad', 'https://http2.mlstatic.com/D_989992-MLA82677966926_032025-C.jpg', NULL, NULL, NULL, NULL, 1),
    ('Radiador de Agua', true, 5, 32000.00, 12, 8, 'Behr', 'REFRIGERACION', 'Radiador de aluminio con núcleo reforzado', 'https://http2.mlstatic.com/D_NQ_NP_720809-MLA69117741608_042023-O.webp', NULL, NULL, NULL, NULL, 3),
    ('Amortiguador Delantero', true, 4.4, 27000.00, 5, 15, 'Monroe', 'MOTOR', 'Amortiguador delantero hidráulico para autos compactos', 'https://http2.mlstatic.com/D_NQ_NP_661399-MLA47933090012_102021-O.webp', NULL, NULL, NULL, NULL, 2),
    ('Aceite Sintético 5W30', true, 3.4, 15000.00, 8, 50, 'Mobil', 'LUBRICACION', 'Aceite sintético de alto rendimiento 5W30 para motores modernos', 'https://www.mysrepuestostoyota.com.ar/wp-content/uploads/2021/05/ACEITE-SINTETICO-5W30-MOBIL1-e1634826196213.jpg', NULL, NULL, NULL, NULL, 1),
    ('Kit Distribución', true, 2.3, 45000.00, 10, 12, 'Contitech', 'MOTOR', 'Kit completo de distribución con correa, tensores y bomba de agua', 'https://http2.mlstatic.com/D_NQ_NP_853402-MLA75075908291_032024-O.webp', NULL, NULL, NULL, NULL, 2),
    ('Ventilador Radiador Doble', true, 3, 50000.00, 15, 6, 'Valeo', 'REFRIGERACION', 'Ventilador de radiador con doble hélice para enfriamiento eficiente', 'https://diegoradiadores.com.uy/wp-content/uploads/2024/02/D_988692-MLU72576297067_102023-F.jpg', NULL, NULL, NULL, NULL, 3),
    ('Filtro de Combustible', true, 4, 6500.00, 0, 30, 'Bosch', 'MOTOR', 'Filtro de combustible para sistemas de inyección modernos', 'https://http2.mlstatic.com/D_NQ_NP_823116-MLA79765070696_102024-O.webp', NULL, NULL, NULL, NULL, 2),
    ('Refrigerante 50%', true, 5, 8000.00, 5, 25, 'Prestone', 'REFRIGERACION', 'Refrigerante premezclado 50% para sistemas de enfriamiento', 'https://http2.mlstatic.com/D_NQ_NP_895399-MLU71662728564_092023-O.webp', NULL, NULL, NULL, NULL, 3),
    ('Juego de Pastillas de Freno', true, 3, 12000.00, 7, 40, 'Brembo', 'MOTOR', 'Pastillas de freno de alto rendimiento para ejes delanteros', 'https://turepuestoonline.com.ar/wp-content/uploads/2023/01/Juego-Pastillas-De-Freno-Delanteras-Lpr-Italia-Vw-Amarok-3.0.webp', NULL, NULL, NULL, NULL, 2),
    ('Aceite de Caja Automática', true, 2, 17000.00, 10, 20, 'Valvoline', 'LUBRICACION', 'Fluido ATF para cajas automáticas de última generación', 'https://http2.mlstatic.com/D_786284-MLA46451360454_062021-C.jpg', NULL, NULL, NULL, NULL, 1),
    ('Intercooler', true, 4, 38000.00, 5, 10, 'Garrett', 'REFRIGERACION', 'Intercooler de alto rendimiento para motores turbo', 'https://http2.mlstatic.com/D_NQ_NP_750419-MLA70027423321_062023-O.webp', NULL, NULL, NULL, NULL, 3);

UPDATE SPARES SET description = 'Aceite sintético de alta calidad diseñado específicamente para motores modernos. Proporciona una excelente protección contra el desgaste, mejora la eficiencia del combustible y garantiza un rendimiento óptimo incluso en condiciones extremas.' WHERE name = 'Aceite 10W40';

UPDATE SPARES SET description = 'Filtro de aire de alto rendimiento fabricado con materiales de primera calidad. Garantiza una filtración eficiente de partículas y mejora el flujo de aire hacia el motor, optimizando su rendimiento y prolongando su vida útil.' WHERE name = 'Filtro de Aire';

UPDATE SPARES SET description = 'Radiador compacto de aluminio diseñado para sistemas de enfriamiento de vehículos. Ofrece una excelente disipación de calor, resistencia a la corrosión y un diseño compacto que facilita su instalación en espacios reducidos.' WHERE name = 'Radiador Compacto';

UPDATE SPARES SET description = 'Juego de 4 bujías de iridio de alta durabilidad. Estas bujías proporcionan una chispa más fuerte y consistente, mejorando la combustión del motor y reduciendo el consumo de combustible.' WHERE name = 'Bujías Iridium';

UPDATE SPARES SET description = 'Batería de 12V y 65Ah diseñada para vehículos medianos. Ofrece una larga duración, alta capacidad de arranque en frío y resistencia a las vibraciones, ideal para condiciones exigentes.' WHERE name = 'Batería 12V 65Ah';

UPDATE SPARES SET description = 'Filtro de aceite compatible con motores diésel y nafteros. Fabricado con materiales de alta calidad para garantizar una filtración eficiente y proteger el motor de impurezas y contaminantes.' WHERE name = 'Filtro de Aceite';

UPDATE SPARES SET description = 'Ventilador eléctrico para sistemas de enfriamiento del motor. Diseñado para proporcionar un flujo de aire constante y eficiente, ayudando a mantener la temperatura óptima del motor en todo momento.' WHERE name = 'Ventilador Electrico';

UPDATE SPARES SET description = 'Correa dentada de 120 dientes fabricada con materiales de alta resistencia. Ideal para motores 1.6, garantiza un funcionamiento preciso y duradero del sistema de distribución del motor.' WHERE name = 'Correa Dentada';

UPDATE SPARES SET description = 'Termostato calibrado a 90°C para la regulación de la temperatura del motor. Fabricado con materiales de alta calidad para garantizar un rendimiento confiable y una larga vida útil.' WHERE name = 'Termostato 90°C';

UPDATE SPARES SET description = 'Aceite de transmisión de alta viscosidad diseñado para cajas de cambios. Proporciona una lubricación superior, reduce el desgaste y mejora el rendimiento de la transmisión en condiciones extremas.' WHERE name = 'Lubricante Transmisión';

UPDATE SPARES SET description = 'Radiador de agua de aluminio con núcleo reforzado. Diseñado para ofrecer una excelente disipación de calor y resistencia a la corrosión, ideal para vehículos que operan en condiciones exigentes.' WHERE name = 'Radiador de Agua';

UPDATE SPARES SET description = 'Amortiguador delantero hidráulico diseñado para autos compactos. Proporciona una conducción suave y estable, absorbiendo eficazmente los impactos y mejorando la seguridad del vehículo.' WHERE name = 'Amortiguador Delantero';

UPDATE SPARES SET description = 'Aceite sintético de alto rendimiento 5W30 para motores modernos. Ofrece una protección superior contra el desgaste, mejora la eficiencia del combustible y garantiza un arranque fácil en climas fríos.' WHERE name = 'Aceite Sintético 5W30';

UPDATE SPARES SET description = 'Kit completo de distribución que incluye correa, tensores y bomba de agua. Diseñado para garantizar un funcionamiento preciso y confiable del sistema de distribución del motor.' WHERE name = 'Kit Distribución';

UPDATE SPARES SET description = 'Ventilador de radiador con doble hélice para un enfriamiento eficiente. Fabricado con materiales de alta calidad, garantiza un flujo de aire constante y una larga vida útil.' WHERE name = 'Ventilador Radiador Doble';

UPDATE SPARES SET description = 'Filtro de combustible diseñado para sistemas de inyección modernos. Proporciona una filtración eficiente de impurezas, protegiendo el sistema de inyección y mejorando el rendimiento del motor.' WHERE name = 'Filtro de Combustible';

UPDATE SPARES SET description = 'Refrigerante premezclado al 50% diseñado para sistemas de enfriamiento. Ofrece una excelente protección contra la corrosión, evita el sobrecalentamiento y prolonga la vida útil del motor.' WHERE name = 'Refrigerante 50%';

UPDATE SPARES SET description = 'Juego de pastillas de freno de alto rendimiento para ejes delanteros. Fabricadas con materiales de alta calidad, garantizan una frenada segura y eficiente en todo tipo de condiciones.' WHERE name = 'Juego de Pastillas de Freno';

UPDATE SPARES SET description = 'Fluido ATF para cajas automáticas de última generación. Proporciona una lubricación superior, reduce el desgaste y mejora el rendimiento de la transmisión, incluso en condiciones extremas.' WHERE name = 'Aceite de Caja Automática';

UPDATE SPARES SET description = 'Intercooler de alto rendimiento diseñado para motores turbo. Mejora la eficiencia del motor al reducir la temperatura del aire comprimido, aumentando la potencia y el rendimiento general.' WHERE name = 'Intercooler';

INSERT INTO EMPLOYEES (
    birthDate,CUIT, typeOfContract, workingDay, bancaryNumber, fullName,
    salary, address, phone, email, position, dateOfEntry, remarks
) VALUES
      ('1990-05-10',20074504039, 'PERMANENT', 'FULLTIME', 8743518309234563439575, 'Juan Pérez',
       850000.00, 'Av. Siempre Viva 123', 543512233445, 'juan.perez@email.com',
       'Tecnico Automotor', '2022-01-15', 'Empleado con buen desempeño'),

      ('1985-08-22',20301230034, 'TEMPORARY', 'PART_TIME', 6543217323952474164726, 'Ana Gómez',
       700000.00, 'Calle Falsa 456', 543519988776, 'ana.gomez@email.com',
       'Personalizacion de Carroceria', '2023-06-01', 'Contrato temporal por 6 meses'),

      ('1992-12-01',21300153054, 'PERMANENT', 'FULLTIME', 1123344934863052082331, 'Carlos Ruiz',
       950000.00, 'Pasaje Luna 789', 543514556912, 'carlos.ruiz@email.com',
       'Tecnico Electromecanico', '2021-09-10', 'Trabaja desde el exterior');


INSERT INTO EMPLOYEES (
    birthDate, CUIT, typeOfContract, workingDay, bancaryNumber, fullName,
    salary, address, phone, email, position, dateOfEntry, remarks
) VALUES
      ('1991-07-15', 20345678901, 'PERMANENT', 'FULLTIME', 2850590940093281723567, 'Lucía Fernández',
       880000.00, 'Calle Principal 123', 543514567891, 'lucia.fernandez@email.com',
       'Administradora', '2020-03-01', 'Excelente desempeño en liderazgo'),

      ('1988-11-20', 20456789012, 'TEMPORARY', 'PART_TIME', 7209335762301984537663, 'Martín López',
       720000.00, 'Av. Libertad 456', 543515679012, 'martin.lopez@email.com',
       'Mecanico', '2023-07-15', 'Contrato temporal por 1 año'),

      ('1993-02-10', 20567890123, 'PERMANENT', 'REMOTE', 1430019256148200375501, 'Sofía Martínez',
       940000.00, 'Pasaje Estrella 789', 543516780123, 'sofia.martinez@email.com',
       'Tecnino Automotor', '2021-10-01', 'Especialista en análisis de grandes volúmenes de datos');

-- CUSTOMIZATION
INSERT INTO service_entity (
    nombre_completo, observaciones_previas, auto, modelo, status,
    payment_status, type, date_entry, date_exit, cost,
    dtype, materials_used, task_realized
) VALUES
      ('Laura Torres', 'Cambio de color interior', 'Volkswagen Polo', 2022, 'WITHDRAW',
       'PAID', 'CUSTOMIZATION', '2025-05-15 10:00:00', '2025-05-16 16:00:00', 60000.0,
       'CustomizationEntity', 'Vinilo beige, pegamento industrial', 'Cambio de color interior completo'),

      ('Fernando Díaz', 'Agregar luces LED', 'Chevrolet Cruze', 2020, 'WITHDRAW',
       'UNPAID', 'CUSTOMIZATION', '2025-05-17 09:00:00', '2025-05-17 12:00:00', 25000.0,
       'CustomizationEntity', 'Luces LED blancas', 'Instalación de luces LED en cabina y baúl'),
      (
          'Luis Fernández', 'Quiere cambiar tapizado', 'Chevrolet Onix', 2021, 'WAITING',
          'PAID', 'CUSTOMIZATION', '2025-04-28 10:00:00', '2025-05-04 18:00:00', 75000.0,
          'CustomizationEntity', 'Cuero ecológico, pegamento industrial', 'Cambio completo de tapizado interior'
      ),
      (
          'Andrea Núñez', 'Agregar vinilo decorativo', 'Mazda 3', 2023, 'WITHDRAW',
          'UNPAID', 'CUSTOMIZATION', '2025-05-09 09:00:00', '2025-05-09 13:00:00', 300.0,
          'CustomizationEntity', 'Vinilo negro mate', 'Aplicación de vinilo en capó y laterales'
      ),
      (
          'Diego Paredes', 'Cambio de luces interiores', 'Hyundai Elantra', 2018, 'WITHDRAW',
          'UNPAID', 'CUSTOMIZATION', '2025-05-14 11:00:00', '2025-05-14 13:30:00', 18000.0,
          'CustomizationEntity', 'Luces LED azules', 'Instalación de luces LED en cabina'
      ),
      (
          'Roberto Díaz', 'Quiere personalizar volante', 'Peugeot 208', 2022, 'WITHDRAW',
          'PAID', 'CUSTOMIZATION', '2025-05-05 14:00:00', '2025-05-06 12:00:00', 20000.0,
          'CustomizationEntity', 'Cuero negro, hilo rojo', 'Tapizado de volante personalizado'
      );


-- INSPECTION
-- INSPECTION
INSERT INTO service_entity (
    nombre_completo, observaciones_previas, auto, modelo, status,
    payment_status, type, date_entry, date_exit, cost,
    dtype, resultado, recomendaciones, estado_general
) VALUES (
             'Pedro Alvarado', 'Chequeo post viaje largo', 'Nissan Versa', 2015, 'WITHDRAW',
             'UNPAID', 'INSPECTION', '2025-05-12 08:00:00', '2025-05-12 08:45:00', 110000.0,
             'InspectionEntity', 'Apto', 'Revisar nivel de aceite en 2 meses', 'Apto'
         ),
         ('Gabriel López', 'Chequeo general', 'Toyota Hilux', 2019, 'WITHDRAW',
          'PAID', 'INSPECTION', '2025-05-18 08:00:00', '2025-05-18 09:30:00', 120000.0,
          'InspectionEntity', 'Apto', 'Revisar frenos en 6 meses', 'Apto'),

         ('Carla Méndez', 'Revisión de motor', 'Ford Ranger', 2021, 'WITHDRAW',
          'UNPAID', 'INSPECTION', '2025-05-19 10:00:00', '2025-05-19 11:00:00', 150000.0,
          'InspectionEntity', 'Apto con observaciones', 'Cambio de aceite recomendado', 'Condicional'),
         ('María Torres', 'Chequeo de frenos', 'Renault Sandero', 2017, 'WITHDRAW',
          'PAID', 'INSPECTION', '2025-05-29 08:00:00', '2025-05-29 09:00:00', 100000.0,
          'InspectionEntity', 'Apto', 'Revisar frenos en 6 meses', 'Apto'),

         ('Carlos Díaz', 'Revisión de motor', 'Honda CR-V', 2019, 'WITHDRAW',
          'UNPAID', 'INSPECTION', '2025-05-30 10:00:00', '2025-05-30 11:30:00', 15000.0,
          'InspectionEntity', 'Apto con observaciones', 'Cambio de aceite recomendado', 'Condicional'),

         ('Ana Méndez', 'Chequeo general', 'Hyundai Tucson', 2020, 'WITHDRAW',
          'PAID', 'INSPECTION', '2025-06-01 09:00:00', '2025-06-01 10:00:00', 12000.0,
          'InspectionEntity', 'Apto', 'Próxima revisión en 12 meses', 'Apto'),

         ('Jorge Ramírez', 'Control de luces', 'Chevrolet Tracker', 2018, 'WITHDRAW',
          'PAID', 'INSPECTION', '2025-06-02 08:30:00', '2025-06-02 09:15:00', 90000.0,
          'InspectionEntity', 'Apto', 'Revisar luces en 6 meses', 'Apto'),

         ('Lucía Fernández', 'Chequeo post viaje', 'Toyota Corolla', 2016, 'WITHDRAW',
          'UNPAID', 'INSPECTION', '2025-06-03 08:00:00', '2025-06-03 08:45:00', 11000.0,
          'InspectionEntity', 'Apto', 'Revisar nivel de aceite en 2 meses', 'Apto'),
         (
             'Juan Pérez', 'Ruido en motor', 'Toyota Corolla', 2020, 'FINISHED',
             'UNPAID', 'INSPECTION', '2025-05-01 09:00:00', '2025-05-01 10:00:00', 100000.0,
             'InspectionEntity', 'Apto', 'Revisar cada 6 meses', 'Apto'
         ),
         (
             'Carlos Ménendez', 'Verificación anual', 'Honda Civic', 2019, 'WITHDRAW',
             'PAID', 'INSPECTION', '2025-04-15 08:00:00', '2025-04-15 09:00:00', 12000.0,
             'InspectionEntity', 'Apto', 'Próxima revisión en 12 meses', 'Apto'
         ),
         (
             'Sofía Ramírez', 'Control general', 'Renault Logan', 2016, 'WITHDRAW',
             'PAID', 'INSPECTION', '2025-05-07 08:15:00', '2025-05-07 09:10:00', 95000.0,
             'InspectionEntity', 'Apto con observaciones', 'Revisar frenos en 3 meses', 'Condicional'
         );


-- REPAIR

-- REPAIR
INSERT INTO service_entity (
    nombre_completo, observaciones_previas, auto, modelo, status,
    payment_status, type, date_entry, date_exit, cost,
    dtype, technicla_diagnosis, tasks_performed, spares_used
) VALUES (
             'Camila Herrera', 'Golpe en el paragolpes', 'Kia Rio', 2020, 'WITHDRAW',
             'PAID', 'REPAIR', '2025-05-13 09:00:00', '2025-05-13 17:00:00', 45000.0,
             'RepairEntity', 'Deformación leve en paragolpes delantero', 'Reparación y pintura', 'Masilla, pintura, lija'
         ),
         ('Pedro López', 'Problema en frenos', 'Ford Ranger', 2017, 'WITHDRAW',
          'PAID', 'REPAIR', '2025-06-04 09:00:00', '2025-06-04 14:00:00', 40000.0,
          'RepairEntity', 'Desgaste en pastillas de freno', 'Cambio de pastillas y ajuste de frenos', 'Pastillas de freno'),

         ('Clara Gómez', 'Fallo en sistema eléctrico', 'Nissan Sentra', 2019, 'WITHDRAW',
          'UNPAID', 'REPAIR', '2025-06-05 08:30:00', '2025-06-05 12:00:00', 35000.0,
          'RepairEntity', 'Fusibles quemados', 'Reemplazo de fusibles y revisión de cableado', 'Fusibles, cableado'),

         ('Luis Herrera', 'Golpe en puerta trasera', 'Toyota Hilux', 2020, 'WITHDRAW',
          'PAID', 'REPAIR', '2025-06-06 10:00:00', '2025-06-06 16:00:00', 50000.0,
          'RepairEntity', 'Deformación en puerta', 'Reparación y pintura', 'Masilla, pintura'),

         ('Sofía Méndez', 'Problema en suspensión', 'Mazda CX-5', 2021, 'WITHDRAW',
          'PAID', 'REPAIR', '2025-06-07 09:00:00', '2025-06-07 15:00:00', 60000.0,
          'RepairEntity', 'Amortiguadores desgastados', 'Cambio de amortiguadores', 'Amortiguadores'),

         ('Fernando Pérez', 'Fallo en sistema de encendido', 'Volkswagen Jetta', 2018, 'WITHDRAW',
          'UNPAID', 'REPAIR', '2025-06-08 08:00:00', '2025-06-08 12:00:00', 30000.0,
          'RepairEntity', 'Bujías dañadas', 'Reemplazo de bujías', 'Bujías'),
         ('Jorge Ramírez', 'Falla en sistema de frenos', 'Renault Duster', 2018, 'WITHDRAW',
          'PAID', 'REPAIR', '2025-05-20 09:00:00', '2025-05-20 14:00:00', 40000.0,
          'RepairEntity', 'Desgaste en pastillas de freno', 'Cambio de pastillas y ajuste de frenos', 'Pastillas de freno'),

         ('Lucía Fernández', 'Problema en sistema eléctrico', 'Hyundai Tucson', 2020, 'WITHDRAW',
          'UNPAID', 'REPAIR', '2025-05-21 08:30:00', '2025-05-21 12:00:00', 35000.0,
          'RepairEntity', 'Fusibles quemados', 'Reemplazo de fusibles y revisión de cableado', 'Fusibles, cableado'),
         (
             'María Gómez', 'No enciende', 'Ford Focus', 2018, 'PROCESS',
             'UNPAID', 'REPAIR', '2025-05-02 08:30:00', '2025-05-03 15:00:00', 50000.0,
             'RepairEntity', 'Fallo en sistema eléctrico', 'Cambio de fusibles y revisión de cableado', 'Fusibles, batería'
         ),
         (
             'Valeria Suárez', 'Frenos fallan', 'Volkswagen Gol', 2017, 'WITHDRAW',
             'UNPAID', 'REPAIR', '2025-05-10 09:30:00', '2025-05-11 13:45:00', 380.0,
             'RepairEntity', 'Discos desgastados', 'Cambio de discos y pastillas', 'Discos, pastillas de freno'
         ),
         (
             'Martín López', 'Luces traseras no funcionan', 'Fiat Argo', 2019, 'WITHDRAW',
             'PAID', 'REPAIR', '2025-05-08 10:00:00', '2025-05-08 11:30:00', 150.0,
             'RepairEntity', 'Cables sueltos en sistema de luces', 'Reparación de conexión eléctrica', 'Cableado'
         );



INSERT INTO service_entity_empleados (service_entity_id, empleados_id) VALUES (1, 4);
INSERT INTO service_entity_empleados (service_entity_id, empleados_id) VALUES (2, 4);
INSERT INTO service_entity_empleados (service_entity_id, empleados_id) VALUES (3, 4);
INSERT INTO service_entity_empleados (service_entity_id, empleados_id) VALUES (4, 1);
INSERT INTO service_entity_empleados (service_entity_id, empleados_id) VALUES (5, 2);
INSERT INTO service_entity_empleados (service_entity_id, empleados_id) VALUES (6, 3);
INSERT INTO service_entity_empleados (service_entity_id, empleados_id) VALUES (7, 2);
INSERT INTO service_entity_empleados (service_entity_id, empleados_id) VALUES (8, 3);
INSERT INTO service_entity_empleados (service_entity_id, empleados_id) VALUES (9, 1);
-- Más registros para service_entity_empleados
INSERT INTO service_entity_empleados (service_entity_id, empleados_id) VALUES (10, 2);
INSERT INTO service_entity_empleados (service_entity_id, empleados_id) VALUES (11, 3);
INSERT INTO service_entity_empleados (service_entity_id, empleados_id) VALUES (12, 1);
INSERT INTO service_entity_empleados (service_entity_id, empleados_id) VALUES (13, 2);
INSERT INTO service_entity_empleados (service_entity_id, empleados_id) VALUES (14, 3);
INSERT INTO service_entity_empleados (service_entity_id, empleados_id) VALUES (15, 4);
INSERT INTO service_entity_empleados (service_entity_id, empleados_id) VALUES (16, 5);
INSERT INTO service_entity_empleados (service_entity_id, empleados_id) VALUES (17, 6);
INSERT INTO service_entity_empleados (service_entity_id, empleados_id) VALUES (18, 4);
INSERT INTO service_entity_empleados (service_entity_id, empleados_id) VALUES (19, 5);
INSERT INTO service_entity_empleados (service_entity_id, empleados_id) VALUES (20, 6);
INSERT INTO service_entity_empleados (service_entity_id, empleados_id) VALUES (21, 4);
INSERT INTO service_entity_empleados (service_entity_id, empleados_id) VALUES (22, 5);
INSERT INTO service_entity_empleados (service_entity_id, empleados_id) VALUES (23, 6);
INSERT INTO service_entity_empleados (service_entity_id, empleados_id) VALUES (24, 4);
INSERT INTO service_entity_empleados (service_entity_id, empleados_id) VALUES (25, 5);
INSERT INTO service_entity_empleados (service_entity_id, empleados_id) VALUES (26, 6);
INSERT INTO service_entity_empleados (service_entity_id, empleados_id) VALUES (27, 4);
INSERT INTO service_entity_empleados (service_entity_id, empleados_id) VALUES(28,5);



INSERT INTO sale_entity (type_payment, user_id, date) VALUES
                                                          ('CASH', 'maria.lopez@gmail.com', '2024-05-10'),
                                                          ('CASH', 'carlos.garcia@gmail.com', '2024-05-12'),
                                                          ('CASH', 'ana.martinez@gmail.com', '2024-08-15'),
                                                          ('CASH', 'juan.gomez@gmail.com', '2024-05-18'),
                                                          ('CASH', 'sofia.fernandez@gmail.com', '2024-05-20'),
                                                          ('CASH', 'lucas.perez@gmail.com', '2024-06-10'),
                                                          ('CASH', 'valeria.suarez@gmail.com', '2024-06-15'),
                                                          ('CASH', 'diego.mendez@gmail.com', '2024-07-01'),
                                                          ('MERCADO_PAGO', 'andrea.nunez@gmail.com', '2024-07-10'),
                                                          ('MERCADO_PAGO', 'pedro.alvarado@gmail.com', '2024-07-20'),
                                                          ('MERCADO_PAGO', 'maria.lopez@gmail.com', '2024-05-22'),
                                                          ('MERCADO_PAGO', 'carlos.garcia@gmail.com', '2024-07-25'),
                                                          ('MERCADO_PAGO', 'ana.martinez@gmail.com', '2024-06-28'),
                                                          ('MERCADO_PAGO', 'juan.gomez@gmail.com', '2024-06-01'),
                                                          ('MERCADO_PAGO', 'sofia.fernandez@gmail.com', '2024-06-03');

INSERT INTO detail_sale_entity (cuantity, price, sale_id, spare_id) VALUES
                                                                 (2, 15000.00, 1, 1),
                                                                 (1, 500.00, 1, 3),
                                                                 (3, 6000.00, 2, 2),
                                                                 (1, 7500.00, 3, 5),
                                                                 (2, 4900.00, 4, 6),
                                                                 (1, 50000.00, 5, 4),
                                                                 (1, 70000.00, 5, 1),
                                                                 (1, 12000.00, 6, 2),
                                                                 (2, 3500.00, 6, 3),
                                                                 (1, 8000.00, 7, 5),
                                                                 (3, 400.00, 7, 6),
                                                                 (2, 10000.00, 8, 1),
                                                                 (1, 50000.00, 9, 4),
                                                                 (2, 15000.00, 9, 2),
                                                                 (1, 70000.00, 10, 1),
                                                                 (1, 12000.00, 11, 2),
                                                                 (2, 8000.00, 11, 5),
                                                                 (3, 4500.00, 12, 3),
                                                                 (1, 60000.00, 12, 4),
                                                                 (2, 15000.00, 13, 1),
                                                                 (1, 5000.00, 13, 6),
                                                                 (2, 20000.00, 14, 2),
                                                                 (1, 7500.00, 14, 5),
                                                                 (3, 10000.00, 15, 4),
                                                                 (1, 70000.00, 15, 1);