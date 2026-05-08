import { useEffect, useState } from "react";

type Sportlane = {
  id: number;
  nimi: string;
  riik: string;
}

function HomePage() {
  const [sportlased, setSportlased] = useState<Sportlane[]>([]);

  useEffect(() => {
    fetch("http://localhost:8080/sportlased")
      .then(res => res.json())
      .then(json => setSportlased(json));
  }, []);

  const kustuta = async (id: number) => {
    await fetch(`http://localhost:8080/sportlased/${id}`, {
      method: "DELETE"
    });
    setSportlased(sportlased.filter(s => s.id !== id));
  };

  return (
    <div>
      <h2>Kõik sportlased</h2>
      {sportlased.map(s => (
        <div key={s.id}>
          {s.nimi} - {s.riik}
          <button onClick={() => kustuta(s.id)}>Kustuta</button>
        </div>
      ))}
    </div>
  );
}

export default HomePage;