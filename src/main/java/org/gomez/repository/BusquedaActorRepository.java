        package org.gomez.repository;

        import org.gomez.entity.Actor;

        import java.util.List;

        public interface BusquedaActorRepository {

            // declaracion de metodos de interfaces

            List<Actor> listaActorMuertoNacion(String nacion);
            List<Object[]> actorMayorParticipacion();


        }
