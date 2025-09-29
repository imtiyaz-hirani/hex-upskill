import { useState } from 'react'
import '../css/Login.css'
import axios from 'axios'
import { useNavigate } from 'react-router-dom'
const Login = () => {
    const [username,setUsername] = useState('')
    const [password,setPassword] = useState('')
    const [msg,setMsg] = useState('')
    const navigate = useNavigate();

    const processLogin =async (e)=>{
        e.preventDefault()
        let headers = {
            'Authorization' : 'Basic ' + window.btoa(username + ':' + password)
        }
        if(validate()){
           try{
             let response = await axios.get('http://localhost:8081/api/auth/login', {
                headers
            })
            const {username,password,role}  = response.data; //destructuring 
            switch(role){
                case 'CUSTOMER':
                    navigate('/customer')
                    break; 
                case 'EXECUTIVE':
                    navigate('/executive')
                    break; 
                default:
                    setMsg('Invalid request') 
                    break; 
            }
            
           }
           catch(err){
                setMsg(err)
           }
        }

    }

    const validate = ()=>{
        if(username === ""){
            setMsg('Username required..')
            return
        }
        if(password === ""){
            setMsg('Password required..')
            return
        }
        return true

    }
    return (
        <div className="login-card">
            <div className='row'>
                <div className="col-sm-3">  </div>
                <div className="card col-md-6">
                    <form onSubmit={(e)=> processLogin(e)}>
                        <div className="card-header">
                            Login
                        </div>
                        <div className="card-body">
                            {
                                msg === ""?"":<div className='mb-2 alert alert-danger'>
                                {msg}
                            </div>
                            }
                            
                            <div className="mb-2">
                                <label>Username: </label>
                                <input type="text" className="form-control" 
                                onChange={(e)=>{setUsername(e.target.value); setMsg('')}}
                                />
                            </div>
                            <div className="mb-2">
                                <label>Password: </label>
                                <input type="password" className="form-control" 
                                onChange={(e)=>setPassword(e.target.value)}/>
                            </div>
                            <div className="mb-2">
                                <input type="submit" className="btn btn-primary" />
                            </div>
                        </div>
                    </form>
                </div>
                <div className="col-sm-3">  </div>
            </div>


        </div>

    )
}

export default Login