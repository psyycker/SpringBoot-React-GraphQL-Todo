import React from 'react';
import styled from 'styled-components';
import {TodoObject} from "../types/Todo";

type TodoItemProps = {
  item: TodoObject
}

const Container = styled.div`
  width: 100%;
  display: flex;
`

const SubContainer = styled.div`

width: 90%;
min-height: 200px;
display: flex;
border: 1px solid #e0e0e0;
margin-top: 10px;
border-radius: 6px;
background-color: white;
transition: box-shadow 0.1s ease-in-out;
padding: 0;

@media only screen and (max-width: 768px) {
  width: 100%;
}

&:hover {
  box-shadow: 0 1px 2px 0 rgba(60,64,67,0.302), 0 1px 3px 1px rgba(60,64,67,0.149);
  cursor: pointer;
}
`

export default function TodoItem(props: TodoItemProps): React.ReactElement {
  return (
    <Container>
    <SubContainer>
      {props.item.name}
    </SubContainer>
    </Container>
  )
}
