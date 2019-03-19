package com.basket.manager.entities.teams;

import com.basket.manager.entities.GenericEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "TeamEntity")
@Table(name = "team")
@NamedQueries({
        @NamedQuery(name = "team.getTeamsWithoutPlayers", query = "SELECT t FROM TeamEntity t " +
                "WHERE t.teamPlayers.size = 0"),
})
public class TeamEntity implements GenericEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "team_players", joinColumns = @JoinColumn(name = "team_id"), inverseJoinColumns = @JoinColumn(name = "team_player_id"))
    private Set<TeamPlayerEntity> teamPlayers = new HashSet<>();

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Set<TeamPlayerEntity> getTeamPlayers() {
        return teamPlayers;
    }

    public void setTeamPlayers(Set<TeamPlayerEntity> teamPlayers) {
        this.teamPlayers = teamPlayers;
    }

    public void addTeamPlayer(TeamPlayerEntity teamPlayer) {
        teamPlayers.add(teamPlayer);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
