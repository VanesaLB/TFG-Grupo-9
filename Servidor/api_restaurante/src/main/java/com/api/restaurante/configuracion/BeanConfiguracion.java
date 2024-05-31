package com.api.restaurante.configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.api.restaurante.modelo.dto.PedidoDto;
import com.api.restaurante.modelo.entities.Pedido;

import org.modelmapper.ModelMapper;

@Configuration
public class BeanConfiguracion {

//Aquí declaramos el ModelMapper que usaremos posteriormente en otras clases.
	@Bean
	public ModelMapper modelMapper() {
		 
		/*ModelMapper modelMapper = new ModelMapper();
		   modelMapper.createTypeMap(PedidoDto.class, Pedido.class)
           .addMappings(mapper -> {
                mapper.map(PedidoDto::getIdMesa, Pedido::setMesa);
                mapper.map(PedidoDto::getIdProducto, Pedido::setProducto);
           }); */

		return new ModelMapper();
	}
	
}
