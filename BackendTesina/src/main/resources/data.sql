INSERT INTO USERS (email,name,lastname, password, role) VALUES ('nahuelvegavega@gmail.com', 'Nahuel','Vega','1234', 'SUPERADMIN');

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