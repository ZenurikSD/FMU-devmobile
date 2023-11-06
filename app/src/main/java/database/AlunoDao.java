package database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.OnConflictStrategy;

import java.util.List;

@Dao
public class AlunoDao {
    @Query("SELECT * FROM Aluno")
    List<Aluno> getAlunosList();

    @Query("SELECT * FROM Aluno WHERE ra = :ra_aluno")
    Aluno getAluno(int ra_aluno);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Aluno aluno);

    @Delete
    void delete(Aluno aluno);

    @Update
    void update(Aluno aluno);
}
