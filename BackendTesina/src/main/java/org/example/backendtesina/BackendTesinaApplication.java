package org.example.backendtesina;
import com.mercadopago.MercadoPagoConfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BackendTesinaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendTesinaApplication.class, args);

		// CREDENCIALES FALSAS
		MercadoPagoConfig.setAccessToken("APP_USR-6697469370584294-043012-15ec1f5d090244f4491e77dc501f75e0-2417547238");
		// CREDENCIALES REALES
		//MercadoPagoConfig.setAccessToken("APP_USR-4934766117276882-042915-969a888bc1dadf611b136315fa889b6e-364961137");
	}

}
