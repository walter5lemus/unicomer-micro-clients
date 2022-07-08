package com.unicomer.micro.clients.service.mapper;

import com.unicomer.micro.clients.controller.request.ClientRequest;
import com.unicomer.micro.clients.controller.response.ClientResponse;
import com.unicomer.micro.clients.repository.entity.Client;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-07T20:22:33-0600",
    comments = "version: 1.5.2.Final, compiler: Eclipse JDT (IDE) 1.4.100.v20220318-0906, environment: Java 17.0.2 (Eclipse Adoptium)"
)
@Component
public class ClientMapperImpl implements ClientMapper {

    @Override
    public ClientResponse convertClientToClientResponse(Client client) {
        if ( client == null ) {
            return null;
        }

        ClientResponse.ClientResponseBuilder clientResponse = ClientResponse.builder();

        clientResponse.addressHome( client.getAddressHome() );
        clientResponse.birthday( client.getBirthday() );
        clientResponse.cellPhone( client.getCellPhone() );
        clientResponse.firstName( client.getFirstName() );
        clientResponse.gender( client.getGender() );
        clientResponse.homePhone( client.getHomePhone() );
        clientResponse.idClient( client.getIdClient() );
        clientResponse.incomes( client.getIncomes() );
        clientResponse.lastName( client.getLastName() );
        clientResponse.profession( client.getProfession() );

        return clientResponse.build();
    }

    @Override
    public Client convertClientRequestToClient(ClientRequest client) {
        if ( client == null ) {
            return null;
        }

        Client.ClientBuilder client1 = Client.builder();

        client1.addressHome( client.getAddressHome() );
        client1.birthday( client.getBirthday() );
        client1.cellPhone( client.getCellPhone() );
        client1.firstName( client.getFirstName() );
        client1.gender( client.getGender() );
        client1.homePhone( client.getHomePhone() );
        client1.incomes( client.getIncomes() );
        client1.lastName( client.getLastName() );
        client1.profession( client.getProfession() );

        return client1.build();
    }
}
