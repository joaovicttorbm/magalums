package com.tech.magalums.config;

import java.util.Arrays;

import org.springframework.context.annotation.Configuration;

import com.tech.magalums.entity.Channel;
import com.tech.magalums.entity.Status;
import com.tech.magalums.repository.ChannelRepository;
import com.tech.magalums.repository.StatusRepository;

import jakarta.transaction.Transactional;

@Configuration
public class InitialDataLoader {

    private final ChannelRepository channelRepository;
    private final StatusRepository statusRepository;

    public InitialDataLoader(ChannelRepository channelRepository, StatusRepository statusRepository) {
        this.channelRepository = channelRepository;
        this.statusRepository = statusRepository;
    }

    @Transactional
    public void run(String... args) throws Exception {
        loadChannels();
        loadStatuses();
    }

    private void loadChannels() {
        Arrays.stream(Channel.Values.values())
                .map(Channel.Values::toChannel)
                .forEach(channel -> {
                    if (!channelRepository.existsById(channel.getChannelId())) {
                        channelRepository.save(channel);
                    }
                });
    }

    private void loadStatuses() {
        Arrays.stream(Status.Values.values())
                .map(Status.Values::toStatus)
                .forEach(status -> {
                    if (!statusRepository.existsById(status.getStatusId())) {
                        statusRepository.save(status);
                    }
                });
    }
}
