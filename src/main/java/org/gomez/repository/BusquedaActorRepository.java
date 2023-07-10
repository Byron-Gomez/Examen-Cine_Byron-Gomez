        package org.gomez.repository;

        import org.gomez.entity.Actor;

        import java.util.List;

        public interface BusquedaActorRepository {
            List<Actor> listaActorMuertoNacion(String nacion);
            List<Object[]> actorMayorParticipacion();


        }
