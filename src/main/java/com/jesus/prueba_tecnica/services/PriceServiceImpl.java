package com.jesus.prueba_tecnica.services;

import com.jesus.prueba_tecnica.repository.dao.PriceRepository;
import com.jesus.prueba_tecnica.repository.entity.Price;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Clase de servicio encargada de la logica de las operaciones
 * @author Jesus
 */
@Service
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;

    public PriceServiceImpl(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    /**
     * Metodo que se encarga de llamar al repositorio para que filtre y obtenga el precio
     * @param applicationDate La fecha en la que se va a filtrar
     * @param productId El id del producto por el que se va a filtrar
     * @param brandId El brandId por el que se va a filtrar
     * @return {@link Optional} que contiene el {@link Price} si se encuentra, o un Optional vacío si no hay precio aplicable.
     */
    public Optional<Price> getApplicablePrice(LocalDateTime applicationDate, int productId, int brandId) {
        
        dataValidator(applicationDate, productId, brandId);
        
        return priceRepository.findApplicablePrice(applicationDate, productId, brandId);
    }

    /**
     * Metodo encargado de validar los datos de entrada
     * @param applicationDate La fecha en la que se va a filtrar
     * @param productId El id del producto por el que se va a filtrar
     * @param brandId El brandId por el que se va a filtrar
     */
    private void dataValidator(LocalDateTime applicationDate, int productId, int brandId) {

        if(applicationDate == null){
            throw new IllegalArgumentException("applicationDate cannot be null");
        }

        if(productId < 0){
            throw new IllegalArgumentException("productId cannot be negative");
        }

        if(brandId < 0){
            throw new IllegalArgumentException("brandId cannot be negative");
        }
    }
}
