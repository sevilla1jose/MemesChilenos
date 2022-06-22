package com.jsevilla.memeschilenos.domain.usecase

import com.jsevilla.memeschilenos.domain.entity.Either
import com.jsevilla.memeschilenos.domain.entity.Failure
import com.jsevilla.memeschilenos.domain.entity.MemeEntity
import com.jsevilla.memeschilenos.domain.repository.HomeRepository

class GetListMemesChile(
    private val homeRepository: HomeRepository
) : BaseUseCase<MemeEntity, Any>() {
    override suspend fun run(params: Any): Either<Failure, MemeEntity> =
        homeRepository.getListMemes()
}
