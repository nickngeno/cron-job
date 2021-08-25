package com.kimmy.cronusers.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("retired_users")
public class OldUser {
    @Id
    @Generated
    private Integer id;
    private Integer userId;
}
