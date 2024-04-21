package br.com.alura.ceep.repository

import br.com.alura.ceep.database.dao.NotaDao
import br.com.alura.ceep.model.Nota
import br.com.alura.ceep.webclient.NotaWebClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class NotaRepository(
    private val dao: NotaDao,
    private val webClient: NotaWebClient
) {
    fun buscaTodas(): Flow<List<Nota>> {
        return dao.buscaTodas()
    }

    suspend fun atualizaTodas() {
        withContext(Dispatchers.IO) {
            webClient.buscaTodas()?.let { notas ->
                dao.salva(notas)
            }
        }
    }
}