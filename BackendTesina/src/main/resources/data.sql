INSERT INTO USERS (email,name,lastname, password, role) VALUES ('nahuelvegavega@gmail.com', 'Nahuel','Vega','$2a$10$WhRuuQWNRMKNSzjALksRieneowHvpXjj5NXMxdZHr7Zl.cpFEp4.q', 'SUPERADMIN'),
                                                               ('usuario@gmail.com', 'User','Test','$2a$10$DyDlgM58UpT4W2B2yj5Pqe0cW1tGCUR3K1MellY1dyVvti4fGSXTy', 'USER'),
                                                               ('admin@gmail.com', 'Admin','Test','$2a$10$DyDlgM58UpT4W2B2yj5Pqe0cW1tGCUR3K1MellY1dyVvti4fGSXTy', 'ADMIN'),
                                                               ('empleado@gmail.com', 'Empleado','Test','$2a$10$DyDlgM58UpT4W2B2yj5Pqe0cW1tGCUR3K1MellY1dyVvti4fGSXTy', 'EMPLOYEE') ;

INSERT INTO PROVIDERS (
    name, adress, category, city, country,
    CUIT, email, phone, state, register_date, remarks
) VALUES
      ('Proveedor A', 'Av. Siempre Viva 123','MOTOR', 'CORDOBA', 'ARGENTINA',
       '20-12345678-9', 'contacto@proveedora.com', 3511234567, true, '2024-01-10', 'Proveedor confiable con entrega en 48hs'),

      ('Proveedor B', 'Calle Falsa 456', 'LUBRICACION', 'CORDOBA', 'ARGENTINA',
       '30-87654321-0', 'ventas@proveedorb.com', 3419876543, true, '2024-02-15', 'Requiere seguimiento mensual'),

      ('Proveedor C', 'Ruta 9 km 25', 'REFRIGERACION', 'CORDOBA', 'ARGENTINA',
       '27-11223344-5', 'info@proveedorc.com', 2991122334, false, '2023-11-01', 'Actualmente inactivo por falta de stock');

INSERT INTO SPARES
(name,active,stars, price, discaunt, stock, brand, category, description, image1, image2, image3, image4, image5)
VALUES
    ('Aceite 10W40',true,5.00, 12000.00, 10, 50, 'Shell', 'LUBRICACION', 'Aceite sintético para motores modernos', 'https://http2.mlstatic.com/D_NQ_NP_760369-MLA76111407169_042024-O.webp', NULL, NULL, NULL, NULL),

    ('Filtro de Aire',true,4.00, 4500.00, 5, 30, 'Bosch', 'MOTOR', 'Filtro de aire de alto rendimiento', 'https://http2.mlstatic.com/D_NQ_NP_707748-MLA31604241224_072019-O.webp', NULL, NULL, NULL, NULL),

    ('Radiador Compacto',true,3.00, 25000.00, 15, 10, 'Valeo', 'REFRIGERACION', 'Radiador de aluminio compacto', 'https://http2.mlstatic.com/D_NQ_NP_609279-MLM52131448379_102022-O.webp', NULL, NULL, NULL, NULL),

    ('Bujías Iridium',true,3.33, 6000.00, 0, 80, 'NGK', 'MOTOR', 'Juego de 4 bujías de iridio', 'https://http2.mlstatic.com/D_NQ_NP_991346-MLA72649285452_112023-O.webp', NULL, NULL, NULL, NULL),

    ('Batería 12V 65Ah',true,2.0, 35000.00, 5, 20, 'Willard', 'MOTOR', 'Batería de larga duración para autos medianos', 'https://http2.mlstatic.com/D_NQ_NP_690789-MLA78040694005_072024-O.webp',NULL, NULL, NULL, NULL),

    ('Filtro de Aceite',true,1.0, 3000.00, 0, 100, 'Mann', 'LUBRICACION', 'Filtro de aceite compatible con motores diésel y nafteros', 'https://cdn.motordoctor.de/thumb?id=963560&m=1&n=0&lng=es&ccf=94077854', NULL, NULL, NULL, NULL),

    ('Ventilador Electrico',true,4.0, 18000.00, 10, 15, 'Delphi', 'REFRIGERACION', 'Ventilador para sistema de enfriamiento del motor', 'https://m.media-amazon.com/images/I/71++HklIHTL.jpg', NULL, NULL, NULL, NULL),

    ('Correa Dentada',true,5, 7500.00, 0, 40, 'Gates', 'MOTOR', 'Correa dentada de 120 dientes para motor 1.6', 'https://http2.mlstatic.com/D_NQ_NP_994898-MLA41621919030_052020-O.webp', NULL, NULL, NULL, NULL),

    ('Termostato 90°C',true,4.5, 4200.00, 0, 25, 'Mahle', 'REFRIGERACION', 'Termostato para regulación de temperatura del motor', 'https://http2.mlstatic.com/D_869555-MLA76562425378_062024-C.jpg', NULL, NULL, NULL, NULL),

    ('Lubricante Transmisión',true,1.1, 9800.00, 8, 60, 'Castrol', 'LUBRICACION', 'Aceite para caja de cambios de alta viscosidad', 'https://http2.mlstatic.com/D_989992-MLA82677966926_032025-C.jpg', NULL, NULL, NULL, NULL),

    ('Radiador de Agua',true,5, 32000.00, 12, 8, 'Behr', 'REFRIGERACION', 'Radiador de aluminio con núcleo reforzado', 'https://http2.mlstatic.com/D_NQ_NP_720809-MLA69117741608_042023-O.webp', NULL, NULL, NULL, NULL),

    ('Amortiguador Delantero',true,4.4, 27000.00, 5, 15, 'Monroe', 'MOTOR', 'Amortiguador delantero hidráulico para autos compactos', 'https://http2.mlstatic.com/D_NQ_NP_661399-MLA47933090012_102021-O.webp', NULL, NULL, NULL, NULL),

    ('Aceite Sintético 5W30',true,3.4, 15000.00, 8, 50, 'Mobil', 'LUBRICACION', 'Aceite sintético de alto rendimiento 5W30 para motores modernos', 'https://www.mysrepuestostoyota.com.ar/wp-content/uploads/2021/05/ACEITE-SINTETICO-5W30-MOBIL1-e1634826196213.jpg', NULL, NULL, NULL, NULL),

    ('Kit Distribución',true,2.3, 45000.00, 10, 12, 'Contitech', 'MOTOR', 'Kit completo de distribución con correa, tensores y bomba de agua', 'https://http2.mlstatic.com/D_NQ_NP_853402-MLA75075908291_032024-O.webp', NULL, NULL, NULL, NULL),

    ('Ventilador Radiador Doble',true,3, 50000.00, 15, 6, 'Valeo', 'REFRIGERACION', 'Ventilador de radiador con doble hélice para enfriamiento eficiente', 'https://diegoradiadores.com.uy/wp-content/uploads/2024/02/D_988692-MLU72576297067_102023-F.jpg', NULL,NULL, NULL, NULL),

    ('Filtro de Combustible',true,4, 6500.00, 0, 30, 'Bosch', 'MOTOR', 'Filtro de combustible para sistemas de inyección modernos', 'https://http2.mlstatic.com/D_NQ_NP_823116-MLA79765070696_102024-O.webp', NULL, NULL, NULL, NULL),

    ('Refrigerante 50%',true,5, 8000.00, 5, 25, 'Prestone', 'REFRIGERACION', 'Refrigerante premezclado 50% para sistemas de enfriamiento', 'https://http2.mlstatic.com/D_NQ_NP_895399-MLU71662728564_092023-O.webp', NULL, NULL, NULL, NULL),

    ('Juego de Pastillas de Freno',true,3, 12000.00, 7, 40, 'Brembo', 'MOTOR', 'Pastillas de freno de alto rendimiento para ejes delanteros', 'https://turepuestoonline.com.ar/wp-content/uploads/2023/01/Juego-Pastillas-De-Freno-Delanteras-Lpr-Italia-Vw-Amarok-3.0.webp', NULL, NULL, NULL, NULL),

    ('Aceite de Caja Automática',true,2, 17000.00, 10, 20, 'Valvoline', 'LUBRICACION', 'Fluido ATF para cajas automáticas de última generación', 'https://http2.mlstatic.com/D_786284-MLA46451360454_062021-C.jpg', NULL, NULL, NULL, NULL),

    ('Intercooler',true,4, 38000.00, 5, 10, 'Garrett', 'REFRIGERACION', 'Intercooler de alto rendimiento para motores turbo', 'https://http2.mlstatic.com/D_NQ_NP_750419-MLA70027423321_062023-O.webp', NULL, NULL, NULL, NULL);

INSERT INTO EMPLOYEES (
    birthDate,CUIT, typeOfContract, workingDay, bancaryNumber, fullName,
    salary, address, phone, email, position, dateOfEntry, remarks
) VALUES
      ('1990-05-10',200000000, 'PERMANENT', 'FULLTIME', 12345678, 'Juan Pérez',
       850000.00, 'Av. Siempre Viva 123', 1122334455, 'juan.perez@email.com',
       'Tecnico Automotor', '2022-01-15', 'Empleado con buen desempeño'),

      ('1985-08-22',200000000, 'TEMPORARY', 'PART_TIME', 87654321, 'Ana Gómez',
       700000.00, 'Calle Falsa 456', 1199887766, 'ana.gomez@email.com',
       'Personalizacion de Carroceria', '2023-06-01', 'Contrato temporal por 6 meses'),

      ('1992-12-01',200000000, 'PERMANENT', 'REMOTE', 11223344, 'Carlos Ruiz',
       950000.00, 'Pasaje Luna 789', 1133445566, 'carlos.ruiz@email.com',
       'Tecnico Electronico', '2021-09-10', 'Trabaja desde el exterior');

INSERT INTO service_entity (
    nombre_completo, observaciones_previas, auto, modelo, status,
    payment_status, type, date_entry, date_exit, cost,
    dtype, resultado, recomendaciones, estado_general
) VALUES (
             'Juan Pérez', 'Ruido en motor', 'Toyota Corolla', 2020, 'FINISHED',
             'UNPAID', 'INSPECTION', '2025-05-01 09:00:00', '2025-05-01 10:00:00', 100.0,
             'InspectionEntity', 'Apto', 'Revisar cada 6 meses', 'Apto'
         );
INSERT INTO service_entity (
    nombre_completo, observaciones_previas, auto, modelo, status,
    payment_status, type, date_entry, date_exit, cost,
    dtype, technicla_diagnosis, tasks_performed, spares_used
) VALUES (
           'María Gómez', 'No enciende', 'Ford Focus', 2018, 'PROCESS',
             'UNPAID', 'REPAIR', '2025-05-02 08:30:00', '2025-05-03 15:00:00', 500.0,
             'RepairEntity', 'Fallo en sistema eléctrico', 'Cambio de fusibles y revisión de cableado', 'Fusibles, batería'
         );
INSERT INTO service_entity (
    nombre_completo, observaciones_previas, auto, modelo, status,
    payment_status, type, date_entry, date_exit, cost,
    dtype, materials_used, task_realized
) VALUES (
             'Luis Fernández', 'Quiere cambiar tapizado', 'Chevrolet Onix', 2021, 'WAITING',
             'PAID', 'CUSTOMIZATION', '2025-04-28 10:00:00', '2025-05-04 18:00:00', 750.0,
             'CustomizationEntity', 'Cuero ecológico, pegamento industrial', 'Cambio completo de tapizado interior'
         );
INSERT INTO service_entity (
    nombre_completo, observaciones_previas, auto, modelo, status,
    payment_status, type, date_entry, date_exit, cost,
    dtype, resultado, recomendaciones, estado_general
) VALUES (
             'Carlos Méndez', 'Verificación anual', 'Honda Civic', 2019, 'WITHDRAW',
             'PAID', 'INSPECTION', '2025-04-15 08:00:00', '2025-04-15 09:00:00', 120.0,
             'InspectionEntity', 'Apto', 'Próxima revisión en 12 meses', 'Apto'
         );

INSERT INTO service_entity (
    nombre_completo, observaciones_previas, auto, modelo, status,
    payment_status, type, date_entry, date_exit, cost,
    dtype, technicla_diagnosis, tasks_performed, spares_used
) VALUES (
             'Valeria Suárez', 'Frenos fallan', 'Volkswagen Gol', 2017, 'WITHDRAW',
             'UNPAID', 'REPAIR', '2025-05-10 09:30:00', '2025-05-11 13:45:00', 380.0,
             'RepairEntity', 'Discos desgastados', 'Cambio de discos y pastillas', 'Discos, pastillas de freno'
         );

INSERT INTO service_entity (
    nombre_completo, observaciones_previas, auto, modelo, status,
    payment_status, type, date_entry, date_exit, cost,
    dtype, materials_used, task_realized
) VALUES (
             'Roberto Díaz', 'Quiere personalizar volante', 'Peugeot 208', 2022, 'WITHDRAW',
             'PAID', 'CUSTOMIZATION', '2025-05-05 14:00:00', '2025-05-06 12:00:00', 200.0,
             'CustomizationEntity', 'Cuero negro, hilo rojo', 'Tapizado de volante personalizado'
         );
-- INSPECTION
INSERT INTO service_entity (
    nombre_completo, observaciones_previas, auto, modelo, status,
    payment_status, type, date_entry, date_exit, cost,
    dtype, resultado, recomendaciones, estado_general
) VALUES (
             'Sofía Ramírez', 'Control general', 'Renault Logan', 2016, 'WITHDRAW',
             'PAID', 'INSPECTION', '2025-05-07 08:15:00', '2025-05-07 09:10:00', 95.0,
             'InspectionEntity', 'Apto con observaciones', 'Revisar frenos en 3 meses', 'Condicional'
         );

-- REPAIR
INSERT INTO service_entity (
    nombre_completo, observaciones_previas, auto, modelo, status,
    payment_status, type, date_entry, date_exit, cost,
    dtype, technicla_diagnosis, tasks_performed, spares_used
) VALUES (
             'Martín López', 'Luces traseras no funcionan', 'Fiat Argo', 2019, 'WITHDRAW',
             'PAID', 'REPAIR', '2025-05-08 10:00:00', '2025-05-08 11:30:00', 150.0,
             'RepairEntity', 'Cables sueltos en sistema de luces', 'Reparación de conexión eléctrica', 'Cableado'
         );

-- CUSTOMIZATION
INSERT INTO service_entity (
    nombre_completo, observaciones_previas, auto, modelo, status,
    payment_status, type, date_entry, date_exit, cost,
    dtype, materials_used, task_realized
) VALUES (
             'Andrea Núñez', 'Agregar vinilo decorativo', 'Mazda 3', 2023, 'WITHDRAW',
             'UNPAID', 'CUSTOMIZATION', '2025-05-09 09:00:00', '2025-05-09 13:00:00', 300.0,
             'CustomizationEntity', 'Vinilo negro mate', 'Aplicación de vinilo en capó y laterales'
         );
-- INSPECTION
INSERT INTO service_entity (
    nombre_completo, observaciones_previas, auto, modelo, status,
    payment_status, type, date_entry, date_exit, cost,
    dtype, resultado, recomendaciones, estado_general
) VALUES (
             'Pedro Alvarado', 'Chequeo post viaje largo', 'Nissan Versa', 2015, 'WITHDRAW',
             'UNPAID', 'INSPECTION', '2025-05-12 08:00:00', '2025-05-12 08:45:00', 110.0,
             'InspectionEntity', 'Apto', 'Revisar nivel de aceite en 2 meses', 'Apto'
         );

-- REPAIR
INSERT INTO service_entity (
    nombre_completo, observaciones_previas, auto, modelo, status,
    payment_status, type, date_entry, date_exit, cost,
    dtype, technicla_diagnosis, tasks_performed, spares_used
) VALUES (
             'Camila Herrera', 'Golpe en el paragolpes', 'Kia Rio', 2020, 'WITHDRAW',
             'PAID', 'REPAIR', '2025-05-13 09:00:00', '2025-05-13 17:00:00', 450.0,
             'RepairEntity', 'Deformación leve en paragolpes delantero', 'Reparación y pintura', 'Masilla, pintura, lija'
         );

-- CUSTOMIZATION
INSERT INTO service_entity (
    nombre_completo, observaciones_previas, auto, modelo, status,
    payment_status, type, date_entry, date_exit, cost,
    dtype, materials_used, task_realized
) VALUES (
             'Diego Paredes', 'Cambio de luces interiores', 'Hyundai Elantra', 2018, 'WITHDRAW',
             'UNPAID', 'CUSTOMIZATION', '2025-05-14 11:00:00', '2025-05-14 13:30:00', 180.0,
             'CustomizationEntity', 'Luces LED azules', 'Instalación de luces LED en cabina'
         );


INSERT INTO sale_entity (type_payment, user_id, date) VALUES
                                                   ('CASH', 'usuario@gmail.com', '2024-05-10'),
                                                   ('CASH', 'usuario@gmail.com', '2024-05-12'),
                                                   ('CASH', 'usuario@gmail.com', '2024-08-15'),
                                                   ('CASH', 'usuario@gmail.com', '2024-05-18'),
                                                   ('CASH', 'usuario@gmail.com', '2024-05-20'),
('MERCADO_PAGO', 'usuario@gmail.com', '2024-05-22'),
('MERCADO_PAGO', 'usuario@gmail.com', '2024-07-25'),
('MERCADO_PAGO', 'usuario@gmail.com', '2024-06-28'),
('MERCADO_PAGO', 'usuario@gmail.com', '2024-06-01'),
('MERCADO_PAGO', 'usuario@gmail.com', '2024-06-03');

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

                                                                 (1, 70000.00, 10, 1);