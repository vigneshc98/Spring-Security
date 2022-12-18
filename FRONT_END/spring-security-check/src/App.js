import logo from './logo.svg';
import './App.css';
import {useState} from 'react'
import axios from 'axios';

function App() {

  const [body1, setBody] = useState({
    "login":"login",
    "password":"the-password"
  });

  const {login, password} = body1;

    
  const onFieldChange = (e) =>{
    setBody({...body1, [e.target.name]: e.target.value});
  }

  const onSubmit = async() =>{
    console.log(body1);
    // const res = await fetch('http://localhost:8080/v1/signIn',
    //     {
    //       method: 'POST',
    //       headers: { 'Content-Type': 'application/json' },
    //       body: JSON.stringify({
    //         "login":"login",
    //         "password":"the-password"
    //       })
    //     }
    // );
    // const body = await res.json();
    axios({
      method:'POST',
      url:"http://localhost:8080/v1/signIn",
      data:body1,
      // headers: new Headers({
      //   'Accept': 'application/json',
      //   'Access-Control-Allow-Origin': '*',
      //   'Access-Control-Allow-Headers': 'Origin, X-Requested-With, Content-Type, Accept, Authorization',
      //   'Access-Control-Request-Method': 'GET, POST, DELETE, PUT, OPTIONS',
      // })
    }).then((res)=>{
      console.log("---------");
      console.log(res.data);
      console.log("---------");

    })
    // console.log(body);
  }

  return (
    <div className="App">
      <form action="" onSubmit={onSubmit}>
        <input type="text" name='login' value={login} onChange={onFieldChange} />
        <input type="text" name='password' value={password} onChange={onFieldChange} />
        <input type="submit" value="Submit" />
      </form>
    </div>
  );
}

export default App;
