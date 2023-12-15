package com.edurbs.bill.api.config;

import java.util.List;

import org.hibernate.integrator.spi.Integrator;
import org.hibernate.jpa.boot.spi.IntegratorProvider;

import com.edurbs.bill.api.repository.projection.BillProjection;

import io.hypersistence.utils.hibernate.type.util.ClassImportIntegrator;

public class ClassImportIntegratorIntegratorProvider implements IntegratorProvider {
 
    @Override
    public List<Integrator> getIntegrators() {
        return List.of(
            new ClassImportIntegrator(
                List.of(
                    BillProjection.class
                )
            )
        );
    }
}
