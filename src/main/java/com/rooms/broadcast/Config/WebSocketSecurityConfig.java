package com.rooms.broadcast.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.messaging.MessageSecurityMetadataSourceRegistry;
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;

@Configuration
public class WebSocketSecurityConfig extends AbstractSecurityWebSocketMessageBrokerConfigurer {

    @Override
    protected void configureInbound(MessageSecurityMetadataSourceRegistry messages) {
        messages.simpMessageDestMatchers("/app/rooms/{roomId}")
                .access("@webSecurity.isOwnersRoom(authentication, #roomId)")
                .simpSubscribeDestMatchers("/topic/rooms/{roomId}")
                .access("@webSecurity.isContactersRoom(authentication, #roomId)")
                .anyMessage().permitAll();
    }

    @Override
    protected boolean sameOriginDisabled() {
        return true;
    }
}
