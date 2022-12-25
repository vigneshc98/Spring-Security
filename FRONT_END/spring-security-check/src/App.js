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
    // const res = await fetch('http://localhost:8080/v1/setCookie',
    //     {
    //       method: 'GET'
    //       // headers: { 'Content-Type': 'application/json' }
    //       // body: JSON.stringify({
    //       //   "login":"login",
    //       //   "password":"the-password"
    //       // })
    //     }
    // );
    // const body = await res.json();
    // console.log(body);
    //       console.log("---------");
    //   Object.keys(res).map((item, i)=> console.log(res[item]));
    //   console.log("---------");
    axios({
      method:'POST',
      url:"http://localhost:8080/v1/signIn",
      withCredentials:true,                      //to accept cookies
      data:body1
      // headers: new Headers({
      //   'Accept': 'application/json',
      //   'Access-Control-Allow-Origin': '*',
      //   'Access-Control-Allow-Headers': 'Origin, X-Requested-With, Content-Type, Accept, Authorization',
      //   'Access-Control-Request-Method': 'GET, POST, DELETE, PUT, OPTIONS',
      // })
    }).then((res)=>{
      console.log("---------");
      // Object.keys(res).map((item, i)=> console.log(res[item]));
      console.log(res);
      console.log("---------");
      // Object.keys(res['']).map((item, i)=> console.log(res[item]));
    })
    // console.log(body);
  }

  const getUserDetails= async()=>{
    //     const res = await fetch('http://localhost:8080/v1/users/234/profile',
    //     {
    //       method: 'GET',
    //       credentials: 'same-origin'
    //     }
    // );
    // const body = await res.json();

    axios({
      method:'GET',
      url:"http://localhost:8080/v1/users/234/profile",
      withCredentials:true,            
    }).then((res)=>{
      console.log("---------");
      console.log(res);
      console.log("---------");
    })
  
  }

  return (
    <div className="App">
      <form action="" onSubmit={onSubmit}>
        <input type="text" name='login' value={login} onChange={onFieldChange} />
        <input type="text" name='password' value={password} onChange={onFieldChange} />
        <input type="submit" value="Submit" />
      </form>
      <button onClick={getUserDetails}>click</button>
    </div>
  );
}

export default App;
