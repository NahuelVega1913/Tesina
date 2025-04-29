package org.example.backendtesina;
import com.mercadopago.MercadoPagoConfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendTesinaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendTesinaApplication.class, args);
		MercadoPagoConfig.setAccessToken("APP_USR-4934766117276882-042915-969a888bc1dadf611b136315fa889b6e-364961137");
	}

}
