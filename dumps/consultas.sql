SELECT gcompet.capacidades.nome AS capacidade,
	(SELECT(count( gcompet.capacidades_areas.`CAPACIDADE_id`)))AS Ct,
	gcompet.areas.nome AS area,
	count( gcompet.capacidades_areas.`AREA_id`) AS At
FROM gcompet.capacidades_areas
	INNER JOIN gcompet.capacidades ON 
	 gcompet.capacidades_areas.`CAPACIDADE_id` = gcompet.capacidades.id 
	INNER JOIN gcompet.areas ON 
	 gcompet.capacidades_areas.`AREA_id` = gcompet.areas.id 
WHERE gcompet.capacidades_areas.`CAPACIDADE_id` = 1
GROUP BY gcompet.capacidades_areas.`CAPACIDADE_id`,
	gcompet.capacidades_areas.`AREA_id`
ORDER BY gcompet.capacidades_areas.`CAPACIDADE_id` ASC,
	gcompet.capacidades_areas.`AREA_id` ASC